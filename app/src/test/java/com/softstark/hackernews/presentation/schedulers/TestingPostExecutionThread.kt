package com.softstark.hackernews.presentation.schedulers

import com.softstark.hackernews.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestingPostExecutionThread : PostExecutionThread {
    override val scheduler: Scheduler = Schedulers.trampoline()
}