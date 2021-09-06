package com.softstark.hackernews.data.datasources

import com.softstark.hackernews.data.models.NewsEntity
import com.softstark.hackernews.data.repository.NewsCache
import com.softstark.hackernews.data.repository.NewsDataSources
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class NewsCacheDataSource @Inject constructor(
    private val newsCache: NewsCache
) : NewsDataSources {

    override fun getPopularNews(): Single<List<NewsEntity>> {
        throw UnsupportedOperationException("this action is not applicable for local data sources.")
    }

    override fun isNetworkAvailable(): Single<Boolean> {
        throw UnsupportedOperationException("this action is not applicable for local data sources.")
    }

    override fun getCachedNews(): Single<List<NewsEntity>> {
        return newsCache.getCachedNews()
    }

    override fun saveNews(listNews: List<NewsEntity>): Completable {
        return newsCache.saveNews(listNews)
    }

    override fun addException(newsId: Int): Completable {
        return newsCache.addException(newsId)
    }

    override fun getExceptions(): Single<List<Int>> {
        return newsCache.getExceptions()
    }

}
