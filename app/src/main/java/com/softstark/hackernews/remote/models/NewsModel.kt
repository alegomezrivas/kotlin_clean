package com.softstark.hackernews.remote.models

import com.google.gson.annotations.SerializedName

class NewsModel(

    @SerializedName("story_id")
    var id: Int? = 0,

    @SerializedName("author")
    var author: String? = "",

    @SerializedName("story_title")
    var title: String? = "",

    @SerializedName("created_at")
    var createAt: String? = "",

    @SerializedName("story_url")
    var url: String? = "",

    )
