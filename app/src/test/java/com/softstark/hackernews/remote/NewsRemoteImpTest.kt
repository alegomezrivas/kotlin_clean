package com.softstark.hackernews.remote

import com.softstark.hackernews.remote.factory.news.RemoteNewsFactory
import com.softstark.hackernews.remote.mapper.news.NewsModelEntityMapper
import com.softstark.hackernews.remote.models.NewsListModel
import com.softstark.hackernews.remote.services.NewsService
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsRemoteImpTest {

    @Mock
    private lateinit var newsService: NewsService

    private lateinit var newsModelEntityMapper: NewsModelEntityMapper

    private lateinit var newsRemoteImp: NewsRemoteImp

    @Before
    fun setUp() {
        newsModelEntityMapper = NewsModelEntityMapper()

        newsRemoteImp =
            NewsRemoteImp(newsService, newsModelEntityMapper)
    }

    @Test
    fun getPopularNews_Completes() {
        // Arrange
        stubPopularNews(Single.just(RemoteNewsFactory.generateNewsListModel(6, 0)))

        // Act
        val testObserver = newsRemoteImp.getPopularNews().test()

        // Assert
        testObserver.assertComplete()
    }

    @Test
    fun getPopularNews_returnsData() {
        // Arrange
        stubPopularNews(Single.just(RemoteNewsFactory.generateNewsListModel(6, 0)))

        // Act
        val testObserver = newsRemoteImp.getPopularNews().test()

        // Assert
        assert(testObserver.values()[0].size == 6)
    }

    /**
     * Stub helpers
     */

    private fun stubPopularNews(single: Single<NewsListModel>) {
        `when`(newsService.getPopularsNews()).thenReturn(
            single
        )
    }
}
