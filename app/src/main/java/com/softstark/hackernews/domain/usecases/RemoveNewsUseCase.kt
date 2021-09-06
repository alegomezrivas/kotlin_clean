package com.softstark.hackernews.domain.usecases

import com.softstark.hackernews.domain.CompletableUseCase
import com.softstark.hackernews.domain.executor.PostExecutionThread
import com.softstark.hackernews.domain.executor.ThreadExecutor
import com.softstark.hackernews.domain.repositories.NewsRepository
import io.reactivex.Completable
import javax.inject.Inject

class RemoveNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<Int>(
    threadExecutor, postExecutionThread
) {
    public override fun buildUseCaseObservable(requestValues: Int?): Completable {
        return newsRepository.removeNews(requestValues!!)
    }
}