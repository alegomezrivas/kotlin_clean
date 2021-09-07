package com.softstark.hackernews.cache.mapper

import com.softstark.hackernews.cache.factory.cached_news.CachedNewsFactory
import com.softstark.hackernews.cache.mapper.news.NewsEntityMapper
import com.softstark.hackernews.cache.models.CachedNews
import com.softstark.hackernews.data.models.NewsEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsEntityMapperTest {

    lateinit var newsEntityMapper: NewsEntityMapper

    @Before
    fun setUp() {
        newsEntityMapper = NewsEntityMapper()
    }

    @Test
    fun mapFromCached() {
        // Arrange
        val cacheNews = CachedNewsFactory.generateCachedNews()

        // Act
        val newsEntity = newsEntityMapper.mapFromCached(cacheNews)

        // Assert
        assertNewsDataEquality(cacheNews, newsEntity)
    }

    @Test
    fun mapToCache() {
        // Arrange
        val newsEntity = CachedNewsFactory.generateNewsEntity()

        // Act
        val cacheNews = newsEntityMapper.mapToCached(newsEntity)

        // Assert
        assertNewsDataEquality(cacheNews, newsEntity)
    }

    private fun assertNewsDataEquality(cachedNews: CachedNews, newsEntity: NewsEntity) {
        Assert.assertEquals(newsEntity.id, cachedNews.newsId)
        Assert.assertEquals(newsEntity.author, cachedNews.author)
        Assert.assertEquals(newsEntity.title, cachedNews.title)
        Assert.assertEquals(newsEntity.createAt, cachedNews.createAt)
        Assert.assertEquals(newsEntity.url, cachedNews.url)
    }
}
