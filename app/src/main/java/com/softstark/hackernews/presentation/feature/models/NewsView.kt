package com.softstark.hackernews.presentation.feature.models

data class NewsView(
    var id: Int? = 0,
    var author: String? = "",
    var title: String? = "",
    var createAt: String? = "",
    var url: String? = "",
)