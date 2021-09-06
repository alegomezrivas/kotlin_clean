package com.softstark.hackernews.remote.services

import com.softstark.hackernews.remote.models.NewsListModel
import io.reactivex.Single
import retrofit2.http.GET

interface NewsService {

    @GET("search_by_date?query=mobile")
    fun getPopularsNews(): Single<NewsListModel>

}