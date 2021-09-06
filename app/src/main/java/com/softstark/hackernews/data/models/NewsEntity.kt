package com.softstark.hackernews.data.models

data class NewsEntity(
    var id: Int? = 0,
    var author: String? = "",
    var title: String? = "",
    var createAt: String? = "",
    var url: String? = "",
)