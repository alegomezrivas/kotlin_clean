package com.softstark.hackernews.domain.factory.news

import com.softstark.hackernews.domain.entities.News
import com.softstark.hackernews.domain.factory.DataFactory


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
