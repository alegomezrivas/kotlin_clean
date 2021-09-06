package com.softstark.hackernews.data.datasources

import com.softstark.hackernews.data.repository.NewsDataSources
import javax.inject.Inject

open class NewsDataSourceFactory @Inject constructor(
    private val newsCacheDataSource: NewsCacheDataSource,
    private val newsRemoteDataSource: NewsRemoteDataSource
) {

    open fun getDataStore(isCached: Boolean): NewsDataSources {
        if (isCached) {
            return getCacheDataStore()
        }
        return getRemoteDataStore()
    }

    fun getCacheDataStore(): NewsDataSources {
        return newsCacheDataSource
    }

    fun getRemoteDataStore(): NewsDataSources {
        return newsRemoteDataSource
    }
}
