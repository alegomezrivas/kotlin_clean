package com.softstark.hackernews.domain.repositories

import com.softstark.hackernews.domain.entities.News
import io.reactivex.Completable
import io.reactivex.Single

interface NewsRepository {
    fun getPopularNews(): Single<List<News>>
    fun removeNews(newsId: Int) : Completable
}