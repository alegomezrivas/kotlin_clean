package com.softstark.hackernews.core.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.softstark.hackernews.R
import com.softstark.hackernews.core.MainApplication
import com.softstark.hackernews.core.extension.createFactory
import com.softstark.hackernews.core.extension.gone
import com.softstark.hackernews.core.extension.visible
import com.softstark.hackernews.core.ui.adapter.ItemsViewModel
import com.softstark.hackernews.core.ui.adapter.NewsListAdapter
import com.softstark.hackernews.core.ui.adapter.SwipeToDeleteCallback
import com.softstark.hackernews.domain.usecases.GetNewsListUseCase
import com.softstark.hackernews.domain.usecases.RemoveNewsUseCase
import com.softstark.hackernews.presentation.feature.mapper.NewsMapper
import com.softstark.hackernews.presentation.feature.models.NewsView
import com.softstark.hackernews.presentation.feature.viewmodel.NewsListViewModel
import com.softstark.hackernews.presentation.feature.viewmodel.NewsState
import javax.inject.Inject
import com.google.android.material.snackbar.Snackbar


class NewsListActivity : AppCompatActivity(), NewsListAdapter.OnClickedListener {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, NewsListActivity::class.java))
        }
    }

    private lateinit var rootRecyclerView: SwipeRefreshLayout

    private lateinit var recyclerview: RecyclerView

    private lateinit var newsListViewModel: NewsListViewModel

    private var newsListAdapter: NewsListAdapter? = null

    @Inject
    lateinit var newsMapper: NewsMapper

    @Inject
    lateinit var getNewsListUseCase: GetNewsListUseCase

    @Inject
    lateinit var removeNewsUseCase: RemoveNewsUseCase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        init()
        onClicks()
        setRecyclerView()
        setObservers()
    }

    private fun init() {
        rootRecyclerView = findViewById(R.id.rootRecyclerView)
        rootRecyclerView.setOnRefreshListener {
            Handler().postDelayed({
                rootRecyclerView.isRefreshing = false
            }, 4000)
        }
        rootRecyclerView.setColorSchemeColors(
            resources.getColor(android.R.color.holo_blue_dark),
            resources.getColor(android.R.color.holo_green_dark),
            resources.getColor(android.R.color.holo_orange_dark),
            resources.getColor(android.R.color.holo_red_dark)
        )
        recyclerview = findViewById(R.id.newsListRecyclerView)
        MainApplication.appComponent().inject(NewsListActivity())
        val factory = NewsListViewModel(
            newsMapper,
            getNewsListUseCase,
            removeNewsUseCase
        ).createFactory()
//        newsListViewModel =
//            HasDefaultViewModelProviderFactory.of(this, factory).get(NewsListViewModel::class.java)
    }

    private fun setObservers() {
        newsListViewModel.stateObservable.observe(this, Observer {
            updateView(it)
        })
        fetchNews()
    }

    private fun setRecyclerView() {
        newsListAdapter = NewsListAdapter()
        newsListAdapter?.setRemoveNewsChangeListener(this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = newsListAdapter

        val swipeHandler = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                newsListAdapter!!.removeAt(viewHolder.adapterPosition)
                // backup of removed item for undo purpose
                val snackbar: Snackbar = Snackbar
                    .make(
                        rootRecyclerView,
                        "Are you sure ?",
                        Snackbar.LENGTH_LONG
                    )
                snackbar.setAction("UNDO") {
//                    newsListAdapter!!.restoreItem(, viewHolder.adapterPosition)
                }
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerview)
    }

    private fun fetchNews() {
      newsListViewModel.fetchNewsList()
    }

    private fun updateView(newsState: NewsState) {
        when (newsState) {
            NewsState.Loading -> showLoading()
            is NewsState.Error -> hideLoading()
            is NewsState.NewsListSuccess -> showMovieListToUI(newsState.listOfNewsViews)
        }
    }

    private fun onClicks() {
        rootRecyclerView.setOnRefreshListener {
            fetchNews()
        }
    }

    private fun showMovieListToUI(listOfMovieViews: List<NewsView>) {
        hideLoading()
        newsListAdapter?.refreshList(listOfMovieViews)
        recyclerview.visible()
    }

    private fun showLoading() {
        rootRecyclerView.isRefreshing = true
        recyclerview.gone()
    }

    private fun hideLoading() {
        rootRecyclerView.isRefreshing = false
    }

    override fun onClickChanged(itemView: NewsView) {
        newsListViewModel.onRemoveNewsStatusChanged(itemView)
    }
}
