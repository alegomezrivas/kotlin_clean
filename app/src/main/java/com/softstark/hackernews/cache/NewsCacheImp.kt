package com.softstark.hackernews.cache

import com.softstark.hackernews.cache.db.NewsDatabase
import com.softstark.hackernews.cache.mapper.news.NewsEntityMapper
import com.softstark.hackernews.data.models.NewsEntity
import com.softstark.hackernews.data.repository.NewsCache
import io.reactivex.Completable
import io.reactivex.Single
import java.util.ArrayList
import javax.inject.Inject

class NewsCacheImp @Inject constructor(
    private val newsDatabase: NewsDatabase,
    private val newsEntityMapper: NewsEntityMapper,
    private val preferencesHelper: PreferencesHelper
) : NewsCache {

    /**
     * Caching Implementations
     */

    override fun saveNews(listNews: List<NewsEntity>): Completable {
        return Completable.defer {
            listNews.map { newsEntityMapper.mapToCached(it) }.forEach {
                newsDatabase.cachedNewsDao().saveNews(it)
            }
            Completable.complete()
        }
    }

    override fun getCachedNews(): Single<List<NewsEntity>> {
        return Single.defer {
            Single.just(newsDatabase.cachedNewsDao().getNews()).map {
                it.map { newsEntityMapper.mapFromCached(it) }
            }
        }
    }

    override fun addException(newsId: Int): Completable {
        preferencesHelper.addExceptionsCached = newsId
        return Completable.complete()
    }

    override fun getExceptions(): Single<ArrayList<String?>> {
        return Single.just(preferencesHelper.listExceptionsCached)
    }

}
