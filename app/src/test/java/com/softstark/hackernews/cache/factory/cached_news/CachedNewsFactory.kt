package com.softstark.hackernews.cache.factory.cached_news

import com.softstark.hackernews.cache.factory.DataFactory
import com.softstark.hackernews.cache.models.CachedNews
import com.softstark.hackernews.data.models.NewsEntity

object CachedNewsFactory {

    fun generateListOfCachedNews(size: Int): MutableList<CachedNews> {
        val listOfNewsMovie = mutableListOf<CachedNews>()
        repeat(size) {
            listOfNewsMovie.add(generateCachedNews())
        }
        return listOfNewsMovie
    }

    fun generateListOfNewsEntities(size: Int): MutableList<NewsEntity> {
        val listOfNewsEntities = mutableListOf<NewsEntity>()
        repeat(size) {
            listOfNewsEntities.add(generateNewsEntity())
        }
        return listOfNewsEntities
    }

    fun generateCachedNews(): CachedNews {
        return CachedNews(
            newsId = DataFactory.getRandomInt(),
            author = DataFactory.getRandomString(),
            title = DataFactory.getRandomString(),
            createAt = DataFactory.getRandomString(),
            url = DataFactory.getRandomString()
        )
    }

    fun generateNewsEntity(): NewsEntity {
        return NewsEntity(
            id = DataFactory.getRandomInt(),
            author = DataFactory.getRandomString(),
            title = DataFactory.getRandomString(),
            createAt = DataFactory.getRandomString(),
            url = DataFactory.getRandomString()

        )
    }
}
