package com.softstark.hackernews.cache

import androidx.room.Room
import com.softstark.hackernews.cache.db.NewsDatabase
import com.softstark.hackernews.cache.factory.cached_news.CachedNewsFactory
import com.softstark.hackernews.cache.mapper.news.NewsEntityMapper
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class NewsCacheImpTest {

    private lateinit var newsDatabase: NewsDatabase

    private lateinit var newsEntityMapper: NewsEntityMapper

    private lateinit var preferencesHelper: PreferencesHelper

    private lateinit var newsCacheImp: NewsCacheImp

    @Before
    fun setUp() {
        preferencesHelper = PreferencesHelper(RuntimeEnvironment.application)
        newsEntityMapper = NewsEntityMapper()
        newsDatabase = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.baseContext,
            NewsDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        newsCacheImp = NewsCacheImp(newsDatabase, newsEntityMapper, preferencesHelper)
    }

    @Test
    fun saveNews_completes() {
        // Arrange
        val newsEntities = CachedNewsFactory.generateListOfNewsEntities(7)

        // Act
        val testObserver = newsCacheImp.saveNews(newsEntities).test()

        // Assert
        testObserver.assertComplete()
    }

    @Test
    fun saveNews_dataSaved() {
        // Arrange
        val newsEntities = CachedNewsFactory.generateListOfNewsEntities(7)

        // Act
        newsCacheImp.saveNews(newsEntities).test()

        // Assert
        val cachedNews = newsDatabase.cachedNewsDao().getNews()
        assert(cachedNews.size == newsEntities.size)
    }

    @Test
    fun getCachedNews_returnsData() {
        // Arrange
        val newsEntities = CachedNewsFactory.generateListOfNewsEntities(7)
        newsCacheImp.saveNews(newsEntities).test()

        // Act
        val testObserver = newsCacheImp.getCachedNews().test()

        // Assert
        assert(testObserver.values()[0].size == newsEntities.size)
    }

    @Test
    fun getCachedNews_completes() {
        // Arrange
        val newsEntities = CachedNewsFactory.generateListOfNewsEntities(7)
        newsCacheImp.saveNews(newsEntities).test()

        // Act
        val testObserver = newsCacheImp.getCachedNews().test()

        // Assert
        testObserver.assertComplete()
    }

    @After
    fun closeDb() {
        newsDatabase.close()
    }
}
