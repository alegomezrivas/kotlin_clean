package com.softstark.hackernews.data.repository

import com.softstark.hackernews.data.models.NewsEntity
import io.reactivex.Single

interface NewsRemote {
    fun getPopularNews(): Single<List<NewsEntity>>
    fun isNetworkAvailable(): Single<Boolean>
}