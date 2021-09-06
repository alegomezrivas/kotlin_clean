package com.softstark.hackernews.remote.factory.news

import com.softstark.hackernews.data.models.NewsEntity
import com.softstark.hackernews.remote.factory.DataFactory
import com.softstark.hackernews.remote.models.NewsListModel
import com.softstark.hackernews.remote.models.NewsModel

object RemoteNewsFactory {

    private const val TOTAL_PAGE_NO = 20
    private const val TOTAL_RESULTS = 50

    fun generateNewsListModel(size: Int, pageNo: Int): NewsListModel {
        val newsListModel = NewsListModel()
        newsListModel.listOfNewsResponse = generateListOfNewsModel(size)
        newsListModel.page = pageNo
        newsListModel.nbPages = TOTAL_PAGE_NO
        newsListModel.resultPerPage = TOTAL_RESULTS
        return newsListModel
    }

    fun generateListOfNewsModel(size: Int): MutableList<NewsModel> {
        val listOfMovieModels = mutableListOf<NewsModel>()
        repeat(size) {
            listOfMovieModels.add(generateNewsModel())
        }
        return listOfMovieModels
    }

    fun generateNewsModel(): NewsModel {
        return NewsModel(
            id = DataFactory.getRandomInt(),
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
