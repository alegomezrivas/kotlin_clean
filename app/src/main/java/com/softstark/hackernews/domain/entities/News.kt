package com.softstark.hackernews.domain.entities

data class News(
    var id: Int? = 0,
    var author: String? = "",
    var title: String? = "",
    var createAt: String? = "",
    var url: String? = "",
)