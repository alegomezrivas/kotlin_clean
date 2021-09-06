package com.softstark.hackernews.remote.mapper

import com.softstark.hackernews.data.models.NewsEntity
import com.softstark.hackernews.remote.factory.news.RemoteNewsFactory
import com.softstark.hackernews.remote.mapper.news.NewsModelEntityMapper
import com.softstark.hackernews.remote.models.NewsModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsModelEntityMapperTest {

    private lateinit var newsModelEntityMapper: NewsModelEntityMapper

    @Before
    fun setUp() {
        newsModelEntityMapper = NewsModelEntityMapper()
    }

    @Test
    fun mapFromModel() {
        // Arrange
        val newsModel = RemoteNewsFactory.generateNewsModel()

        // Act
        val newsEntity = newsModelEntityMapper.mapFromModel(newsModel)

        // Assert
        assertMapNewsDataEquals(newsModel, newsEntity)
    }

    /**
     * Helper Methods
     */
    private fun assertMapNewsDataEquals(newsModel: NewsModel, newsEntity: NewsEntity) {
        assertEquals(newsEntity.id, newsEntity.id)
        assertEquals(newsEntity.author, newsModel.author)
        assertEquals(newsEntity.title, newsModel.title)
        assertEquals(newsEntity.createAt, newsModel.createAt)
        assertEquals(newsEntity.url, newsModel.url)
    }
}
