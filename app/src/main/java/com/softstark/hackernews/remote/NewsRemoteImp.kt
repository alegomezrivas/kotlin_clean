package com.softstark.hackernews.remote

import com.softstark.hackernews.data.models.NewsEntity
import com.softstark.hackernews.data.repository.NewsRemote
import com.softstark.hackernews.remote.mapper.news.NewsModelEntityMapper
import com.softstark.hackernews.remote.services.NewsService
import io.reactivex.Single
import javax.inject.Inject

class NewsRemoteImp @Inject constructor(
    private val newsService: NewsService,
    private val newsModelEntityMapper: NewsModelEntityMapper
) : NewsRemote {

    override fun getPopularNews(): Single<List<NewsEntity>> {
        return newsService.getPopularsNews().map { newsModel ->
            newsModel.listOfNewsResponse.map {
                newsModelEntityMapper.mapFromModel(it)
            }
        }
    }

    override fun isNetworkAvailable(): Single<Boolean> {
        val internetAvailable = true
        return Single.just(internetAvailable).map { internetAvailable }
    }
}
