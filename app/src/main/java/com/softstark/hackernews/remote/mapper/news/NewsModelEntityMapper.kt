package com.softstark.hackernews.remote.mapper.news

import com.softstark.hackernews.data.models.NewsEntity
import com.softstark.hackernews.remote.mapper.EntityMapper
import com.softstark.hackernews.remote.models.NewsModel
import javax.inject.Inject

class NewsModelEntityMapper @Inject constructor() :
    EntityMapper<NewsModel, NewsEntity> {
    override fun mapFromModel(model: NewsModel): NewsEntity {
        return NewsEntity(
            id = model.id,
            author = model.author,
            title = model.title,
            createAt = model.createAt,
            url = model.url
        )
    }
}