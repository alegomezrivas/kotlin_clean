package com.softstark.hackernews.domain.usecases

import com.softstark.hackernews.domain.SingleUseCase
import com.softstark.hackernews.domain.entities.News
import com.softstark.hackernews.domain.executor.PostExecutionThread
import com.softstark.hackernews.domain.executor.ThreadExecutor
import com.softstark.hackernews.domain.repositories.NewsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Void, List<News>>(
    threadExecutor, postExecutionThread
) {
    public override fun buildUseCaseObservable(requestValues: Void?): Single<List<News>> {
        return newsRepository.getPopularNews()
    }
}