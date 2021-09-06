package com.softstark.hackernews.data.interactor

import com.softstark.hackernews.data.factory.news.NewsFactory
import com.softstark.hackernews.domain.entities.News
import com.softstark.hackernews.domain.executor.PostExecutionThread
import com.softstark.hackernews.domain.executor.ThreadExecutor
import com.softstark.hackernews.domain.repositories.NewsRepository
import com.softstark.hackernews.domain.usecases.GetNewsListUseCase
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetNewsListUseCaseTest {

    @Mock
    lateinit var newsRepository: NewsRepository

    @Mock
    lateinit var threadExecutor: ThreadExecutor

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    private lateinit var getNewsListUseCase: GetNewsListUseCase

    @Before
    fun setUp() {
        getNewsListUseCase =
            GetNewsListUseCase(newsRepository, threadExecutor, postExecutionThread)
    }

    @Test
    fun buildUseCaseObservable_call_repository() {
        // Arrange
        // No Arrangement for this test case

        // Act
        getNewsListUseCase.buildUseCaseObservable(null)

        // Assert
        verify(newsRepository).getPopularNews()
    }

    @Test
    fun buildUseCaseObservable_completes() {
        // Arrange
        stubNewsRepositoryGetNewsList(Single.just(NewsFactory.generateDummyNewsList(3)))

        // Act
        val testObserver = getNewsListUseCase.buildUseCaseObservable(null).test()

        // Assert
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservable_returnsData() {
        // Arrange
        val newsList = NewsFactory.generateDummyNewsList(4)
        stubNewsRepositoryGetNewsList(Single.just(newsList))

        // Act
        val testObserver = getNewsListUseCase.buildUseCaseObservable(null).test()

        // Assert
        testObserver.assertValue(newsList)
    }

    private fun stubNewsRepositoryGetNewsList(single: Single<List<News>>) {
        `when`(newsRepository.getPopularNews())
            .thenReturn(single)
    }
}
