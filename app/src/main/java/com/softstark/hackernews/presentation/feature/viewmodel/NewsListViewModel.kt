package com.softstark.hackernews.presentation.feature.viewmodel

import androidx.lifecycle.MutableLiveData
import com.softstark.hackernews.domain.entities.News
import com.softstark.hackernews.domain.usecases.GetNewsListUseCase
import com.softstark.hackernews.domain.usecases.RemoveNewsUseCase
import com.softstark.hackernews.presentation.base.BaseViewModel
import com.softstark.hackernews.presentation.feature.mapper.NewsMapper
import com.softstark.hackernews.presentation.feature.models.NewsView
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver

class NewsListViewModel constructor(
    private val newsMapper: NewsMapper,
    private val getNewsListUseCase: GetNewsListUseCase,
    private val removeNewsUseCase: RemoveNewsUseCase
) : BaseViewModel<NewsState>() {

    private var state: NewsState = NewsState.Init
        set(value) {
            field = value
            publishState(value)
        }

    fun fetchNewsList() {
        state = NewsState.Loading
        val singleObserver = object : DisposableSingleObserver<List<News>>() {
            override fun onSuccess(t: List<News>) {
                state = NewsState.NewsListSuccess(t.map { newsMapper.mapToView(it) })
            }

            override fun onError(e: Throwable) {
                state = NewsState.Error(e.localizedMessage!!)
            }
        }
        getNewsListUseCase.execute(singleObserver)
    }

    fun onRemoveNewsStatusChanged(newsView: NewsView) {
        val singleObserver = object : DisposableCompletableObserver() {

            override fun onComplete() {
                state = NewsState.RemoveNewsSuccess
            }

            override fun onError(e: Throwable) {
                state = NewsState.Error(e.localizedMessage!!)
            }
        }

        removeNewsUseCase.execute(singleObserver, newsView.id)

    }

    override val stateObservable: MutableLiveData<NewsState> by lazy {
        MutableLiveData<NewsState>()
    }
}
