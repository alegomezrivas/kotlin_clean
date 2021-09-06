package com.softstark.hackernews.domain.interactor

import com.softstark.hackernews.domain.executor.PostExecutionThread
import com.softstark.hackernews.domain.executor.ThreadExecutor
import com.softstark.hackernews.domain.repositories.NewsRepository
import com.softstark.hackernews.domain.usecases.RemoveNewsUseCase
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoveNewUseCaseTest {

    @Mock
    lateinit var newsRepository: NewsRepository

    @Mock
    lateinit var threadExecutor: ThreadExecutor

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    lateinit var removeNewsUseCase: RemoveNewsUseCase

    @Before
    fun setUp() {
        removeNewsUseCase =
            RemoveNewsUseCase(newsRepository, threadExecutor, postExecutionThread)
    }

    @Test
    fun buildUseCaseObservable_callRepository() {
        // Arrange
        // No Arrangement for this test case

        // Act
        removeNewsUseCase.buildUseCaseObservable(2)

        // Assert
        Mockito.verify(newsRepository).removeNews(2)
    }

    @Test
    fun buildUseCaseObservable_completes() {
        // Arrange
        val newsId = 3
        stubNewsRepositoryRemoveNews(newsId, Completable.complete())

        // Act
        val test = newsRepository.removeNews(newsId).test()

        // Assert
        test.assertComplete()
    }

    private fun stubNewsRepositoryRemoveNews(newsId: Int, completable: Completable) {
        Mockito.`when`(newsRepository.removeNews(newsId))
            .thenReturn(completable)
    }
}
