package com.softstark.hackernews.presentation.feature.viewmodel

import com.softstark.hackernews.presentation.feature.models.NewsView

sealed class NewsState {
    object Init : NewsState()
    object Loading : NewsState()
    data class Error(var message: String) : NewsState()
    data class NewsListSuccess(var listOfNewsViews: List<NewsView>) : NewsState()
    object RemoveNewsSuccess : NewsState()
}