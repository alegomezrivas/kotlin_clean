package com.softstark.hackernews.presentation.newslist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.softstark.hackernews.domain.entities.News
import com.softstark.hackernews.domain.executor.PostExecutionThread
import com.softstark.hackernews.domain.executor.ThreadExecutor
import com.softstark.hackernews.domain.repositories.NewsRepository
import com.softstark.hackernews.domain.usecases.GetNewsListUseCase
import com.softstark.hackernews.domain.usecases.RemoveNewsUseCase
import com.softstark.hackernews.presentation.factory.news.PresentationNewsFactory
import com.softstark.hackernews.presentation.feature.mapper.NewsMapper
import com.softstark.hackernews.presentation.feature.models.NewsView
import com.softstark.hackernews.presentation.feature.viewmodel.NewsListViewModel
import com.softstark.hackernews.presentation.feature.viewmodel.NewsState
import com.softstark.hackernews.presentation.schedulers.TestingPostExecutionThread
import com.softstark.hackernews.presentation.schedulers.TestingThreadExecutor
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class NewsListViewModelTest {

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var newsRepository: NewsRepository

    @Mock
    private lateinit var stateObserver: Observer<NewsState>

    private var newsMapper = NewsMapper()

    private lateinit var getNewsListUseCase: GetNewsListUseCase

    private lateinit var removeNewsUseCase: RemoveNewsUseCase

    private lateinit var newsListViewModel: NewsListViewModel

    private lateinit var threadExecutor: ThreadExecutor

    private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        setUpThreadForRxJava()
        setUpUseCases()
        setUpViewModel()
    }

    private fun setUpThreadForRxJava() {
        threadExecutor = TestingThreadExecutor()
        postExecutionThread = TestingPostExecutionThread()
    }

    private fun setUpUseCases() {
        getNewsListUseCase =
            GetNewsListUseCase(newsRepository, threadExecutor, postExecutionThread)
        removeNewsUseCase =
            RemoveNewsUseCase(newsRepository, threadExecutor, postExecutionThread)
    }

    private fun setUpViewModel() {
        newsListViewModel = NewsListViewModel(
            newsMapper,
            getNewsListUseCase,
            removeNewsUseCase
        )
        newsListViewModel.stateObservable.observeForever(stateObserver)
    }

    @Test
    fun fetchNewsList_returnsEmpty() {
        // Arrange
        stubFetchNews(Single.just(listOf()))

        // Act
        newsListViewModel.fetchNewsList()

        // Assert
        verify(stateObserver).onChanged(NewsState.Loading)
        verify(stateObserver).onChanged(NewsState.NewsListSuccess(listOf()))
    }

    @Test
    fun fetchNewsList_returnsError() {
        // Arrange
        stubFetchNews(Single.error(TestingException(TestingException.GENERIC_EXCEPTION_MESSAGE)))

        // Act
        newsListViewModel.fetchNewsList()

        // Assert
        verify(stateObserver).onChanged(NewsState.Loading)
        verify(stateObserver).onChanged(NewsState.Error(TestingException.GENERIC_EXCEPTION_MESSAGE))
    }

    @Test
    fun fetchNewsList_returnsData() {
        // Arrange
        val listOfNews = PresentationNewsFactory.generateListOfNews(10)
        val listOfViews = mutableListOf<NewsView>()
        listOfNews.forEach {
            listOfViews.add(newsMapper.mapToView(it))
        }
        stubFetchNews(Single.just(listOfNews))

        // Act
        newsListViewModel.fetchNewsList()

        // Assert
        verify(stateObserver).onChanged(NewsState.Loading)
        verify(stateObserver).onChanged(NewsState.NewsListSuccess(listOfViews))
    }

    @Test
    fun removeNews_error() {
        // Arrange
        val listOfNews = PresentationNewsFactory.generateListOfNews(10)

        val newsToRemove = listOfNews[0] // taking first of list.

        val newsView = newsMapper.mapToView(newsToRemove) // mapping to newsView

        stubFetchNews(Single.just(listOfNews))
        stubRemoveNews(newsToRemove.id!!, Completable.error(TestingException()))

        // Act
        newsListViewModel.onRemoveNewsStatusChanged(newsView)

        // Assert
        verify(stateObserver).onChanged(NewsState.Error(TestingException.GENERIC_EXCEPTION_MESSAGE))
    }

    @Test
    fun removeNews_completes() {
        // Arrange
        val listOfNews = PresentationNewsFactory.generateListOfNews(10)

        val newsToRemove = listOfNews[0] // taking first of list

        val newsView = newsMapper.mapToView(newsToRemove) // mapping to newsView

        stubFetchNews(Single.just(listOfNews))
        stubRemoveNews(newsToRemove.id!!, Completable.complete())

        // Act
        newsListViewModel.onRemoveNewsStatusChanged(newsView)

        // Assert
        verify(stateObserver).onChanged(NewsState.RemoveNewsSuccess)
    }

    /**
     * Stub Helpers Methods
     */
    private fun stubFetchNews(single: Single<List<News>>) {
        `when`(getNewsListUseCase.buildUseCaseObservable())
            .thenReturn(single)
    }

    private fun stubRemoveNews(newsId: Int, completable: Completable) {
        `when`(removeNewsUseCase.buildUseCaseObservable(newsId))
            .thenReturn(completable)
    }

    class TestingException(message: String = GENERIC_EXCEPTION_MESSAGE) : Exception(message) {
        companion object {
            const val GENERIC_EXCEPTION_MESSAGE = "Something error came while executing"
        }
    }
}
