package com.softstark.hackernews.cache.dao

import androidx.room.Room
import com.softstark.hackernews.cache.db.NewsDatabase
import com.softstark.hackernews.cache.factory.cached_news.CachedNewsFactory
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class CachedNewsDaoTest {

    lateinit var newsDatabase: NewsDatabase

    @Before
    fun setUp() {
        newsDatabase = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.baseContext,
            NewsDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    @Test
    fun addNewsToDatabase() {
        // Arrange
        val cachedNews = CachedNewsFactory.generateCachedNews()

        // Act
        newsDatabase.cachedNewsDao().saveNews(cachedNews)

        // Assert
        val cachedNew = newsDatabase.cachedNewsDao().getNews()
        assert(cachedNew.isNotEmpty())
    }

    @Test
    fun clearNews_completes() {
        // Arrange
        val cachedNews = CachedNewsFactory.generateListOfCachedNews(8)
        repeat(cachedNews.size) {
            newsDatabase.cachedNewsDao().saveNews(cachedNews[it])
        }

        // Act
        newsDatabase.cachedNewsDao().clearNews()

        // Assert
        val news = newsDatabase.cachedNewsDao().getNews()
        assert(news.isEmpty())
    }

    @Test
    fun getNews_returnData() {
        // Arrange
        val cachedNews = CachedNewsFactory.generateListOfCachedNews(8)
        repeat(cachedNews.size) {
            newsDatabase.cachedNewsDao().saveNews(cachedNews[it])
        }

        // Act
        val news = newsDatabase.cachedNewsDao().getNews()

        // Assert
        assert(news.size == cachedNews.size)
    }

    @Test
    fun getNews_returnEmpty() {
        // Arrange
        // No Arrangement needed

        // Act
        val news = newsDatabase.cachedNewsDao().getNews()

        // Assert
        assert(news.isEmpty())
    }

    @After
    fun closeDb() {
        newsDatabase.close()
    }
}
