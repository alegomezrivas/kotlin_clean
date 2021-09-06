package com.softstark.hackernews.data.repository

import com.softstark.hackernews.data.models.NewsEntity
import io.reactivex.Completable
import io.reactivex.Single

interface NewsDataSources {
    fun getPopularNews(): Single<List<NewsEntity>>
    fun isNetworkAvailable(): Single<Boolean>
    fun getCachedNews(): Single<List<NewsEntity>>
    fun saveNews(listNews: List<NewsEntity>): Completable
    fun addException(newsId: Int): Completable
    fun getExceptions(): Single<List<Int>>
}