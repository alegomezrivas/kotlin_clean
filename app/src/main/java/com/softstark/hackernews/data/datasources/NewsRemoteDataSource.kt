package com.softstark.hackernews.data.datasources

import com.softstark.hackernews.data.models.NewsEntity
import com.softstark.hackernews.data.repository.NewsDataSources
import com.softstark.hackernews.data.repository.NewsRemote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val newsRemote: NewsRemote
) : NewsDataSources {

    override fun getPopularNews(): Single<List<NewsEntity>> {
        return newsRemote.getPopularNews()
    }

    override fun isNetworkAvailable(): Single<Boolean> {
        return newsRemote.isNetworkAvailable()
    }

    override fun getCachedNews(): Single<List<NewsEntity>> {
        throw UnsupportedOperationException("this action is not applicable for remote data sources.")
    }

    override fun saveNews(listNews: List<NewsEntity>): Completable {
        throw UnsupportedOperationException("this action is not applicable for remote data sources.")
    }

    override fun addException(newsId: Int): Completable {
        throw UnsupportedOperationException("this action is not applicable for remote data sources.")
    }

    override fun getExceptions(): Single<List<Int>> {
        throw UnsupportedOperationException("this action is not applicable for remote data sources.")
    }
}
