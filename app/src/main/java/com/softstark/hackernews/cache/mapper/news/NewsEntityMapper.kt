package com.softstark.hackernews.cache.mapper.news

import com.softstark.hackernews.cache.mapper.EntityMapper
import com.softstark.hackernews.cache.models.CachedNews
import com.softstark.hackernews.data.models.NewsEntity
import javax.inject.Inject

class NewsEntityMapper @Inject constructor() : EntityMapper<CachedNews, NewsEntity> {
    override fun mapFromCached(type: CachedNews): NewsEntity {
        return NewsEntity(
            id = type.newsId,
            author = type.author!!,
            title = type.title!!,
            createAt = type.createAt!!,
            url = type.url!!
        )
    }

    override fun mapToCached(type: NewsEntity): CachedNews {
        return CachedNews(
            newsId = type.id!!,
            author = type.author,
            title = type.title,
            createAt = type.createAt,
            url = type.url
        )
    }
}