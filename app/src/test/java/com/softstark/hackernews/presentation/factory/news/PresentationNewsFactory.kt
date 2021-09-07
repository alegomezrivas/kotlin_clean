package com.softstark.hackernews.presentation.factory.news

import com.softstark.hackernews.domain.entities.News
import com.softstark.hackernews.presentation.factory.DataFactory
import com.softstark.hackernews.presentation.feature.mapper.NewsMapper
import com.softstark.hackernews.presentation.feature.models.NewsView

object PresentationNewsFactory {

    fun generateListOfNewsView(size: Int): MutableList<NewsView> {
        val listOfNewsViews = mutableListOf<NewsView>()
        repeat(size) {
            listOfNewsViews.add(generateNewsView())
        }
        return listOfNewsViews
    }

    fun generateListOfNews(size: Int): MutableList<News> {
        val listOfNews = mutableListOf<News>()
        repeat(size) {
            listOfNews.add(generateNews())
        }
        return listOfNews
    }

    fun generateNewsView(): NewsView {
        return NewsView(
            id = DataFactory.getRandomInt(),
            author = DataFactory.getRandomString(),
            title = DataFactory.getRandomString(),
            createAt = DataFactory.getRandomString(),
            url = NewsMapper.URL_PREFIX.plus(DataFactory.getRandomString())
        )
    }

    fun generateNews(): News {
        return News(
            id = DataFactory.getRandomInt(),
            author = DataFactory.getRandomString(),
            title = DataFactory.getRandomString(),
            createAt = DataFactory.getRandomString(),
            url = DataFactory.getRandomString()

        )
    }
}
