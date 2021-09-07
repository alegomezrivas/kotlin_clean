package com.softstark.hackernews.presentation.feature.mapper

import com.softstark.hackernews.domain.entities.News
import com.softstark.hackernews.presentation.feature.models.NewsView
import com.softstark.hackernews.presentation.mapper.Mapper
import javax.inject.Inject

class NewsMapper @Inject constructor() : Mapper<NewsView, News> {

    override fun mapToView(type: News): NewsView {
        return NewsView(
            id = type.id,
            author = type.author,
            title = type.title,
            createAt = type.createAt,
            url = URL_PREFIX.plus(type.url)
        )
    }

    companion object {
        const val URL_PREFIX = "https://hn.algolia.com/"
    }
}