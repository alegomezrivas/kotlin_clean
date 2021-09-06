package com.softstark.hackernews.remote.models

import com.google.gson.annotations.SerializedName

class NewsListModel(

    @SerializedName("hits")
    var listOfNewsResponse: MutableList<NewsModel> = mutableListOf(),

    @SerializedName("nbHits")
    var nbHits: Int = 0,

    @SerializedName("page")
    var page: Int = 0,

    @SerializedName("nbPages")
    var nbPages: Int = 0,

    @SerializedName("hitsPerPage")
    var resultPerPage: Int = 0,
)
