package com.softstark.hackernews.data.factory.news

import com.softstark.hackernews.data.factory.DataFactory
import com.softstark.hackernews.domain.entities.News

object NewsFactory {

    fun generateDummyNewsList(size: Int): List<News> {
        val newsList = mutableListOf<News>()
        repeat(size) {
            newsList.add(generateNews())
        }
        return newsList
    }

    private fun generateNews(): News {
        return News(
            id = DataFactory.getRandomInt(),
            author = DataFactory.getRandomString(),
            title = DataFactory.getRandomString(),
            createAt = DataFactory.getRandomString(),
            url = DataFactory.getRandomString(),
        )
    }
}