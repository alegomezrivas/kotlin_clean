package com.softstark.hackernews.presentation.newslist.mapper

import com.softstark.hackernews.domain.entities.News
import com.softstark.hackernews.presentation.factory.news.PresentationNewsFactory
import com.softstark.hackernews.presentation.feature.mapper.NewsMapper
import com.softstark.hackernews.presentation.feature.models.NewsView
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsMapperTest {

    private lateinit var newsMapper: NewsMapper

    @Before
    fun setUp() {
        newsMapper = NewsMapper()
    }

    @Test
    fun mapToView() {
        // Arrange
        val news = PresentationNewsFactory.generateNews()

        // Act
        val newsView = newsMapper.mapToView(news)

        // Assert
        assertNewsMapDataEqual(newsView, news)
    }

    /**
     * Helpers Methods
     */
    private fun assertNewsMapDataEqual(newsView: NewsView, news: News) {
        assertEquals(newsView.url, NewsMapper.URL_PREFIX.plus(news.url))
        assertEquals(newsView.id, news.id)
        assertEquals(newsView.author, news.author)
        assertEquals(newsView.title, news.title)
        assertEquals(newsView.createAt, news.createAt)
    }
}
