package com.softstark.hackernews.data.mapper

import com.softstark.hackernews.data.models.NewsEntity
import com.softstark.hackernews.domain.entities.News
import javax.inject.Inject

class NewsMapper @Inject constructor() : Mapper<NewsEntity, News> {
    override fun mapFromEntity(type: NewsEntity): News {
        return News(
            id = type.id,
            author = type.author,
            title = type.title,
            createAt = type.createAt,
            url = type.url
        )
    }

    override fun mapToEntity(type: News): NewsEntity {
        return NewsEntity(
            id = type.id,
            author = type.author,
            title = type.title,
            createAt = type.createAt,
            url = type.url
        )
    }
}