package com.softstark.hackernews.data

import com.softstark.hackernews.data.datasources.NewsDataSourceFactory
import com.softstark.hackernews.data.mapper.NewsMapper
import com.softstark.hackernews.domain.entities.News
import com.softstark.hackernews.domain.repositories.NewsRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class NewsDataRepository @Inject constructor(
    private val newsMapper: NewsMapper,
    private val newsDataSourceFactory: NewsDataSourceFactory
) : NewsRepository {

    override fun getPopularNews(): Single<List<News>> {
        return newsDataSourceFactory.getCacheDataStore().isNetworkAvailable()
            .flatMap {
                newsDataSourceFactory.getDataStore(it).getPopularNews()
            }
            .flatMap { it ->
                Single.just(it.map { newsMapper.mapFromEntity(it) })
            }
            .flatMap {
                saveNews(it).toSingle { it }
            }
    }

    private fun saveNews(listNews: List<News>): Completable {
        return newsDataSourceFactory.getCacheDataStore().saveNews(
            listNews.map { newsMapper.mapToEntity(it) }
        )
    }

    override fun removeNews(newsId: Int): Completable {
        return newsDataSourceFactory.getCacheDataStore().addException(newsId)
    }
}
