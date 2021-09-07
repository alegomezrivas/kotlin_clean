package com.softstark.hackernews.presentation.schedulers

import com.softstark.hackernews.domain.executor.ThreadExecutor
import io.reactivex.schedulers.Schedulers

class TestingThreadExecutor : ThreadExecutor {
    override fun execute(command: Runnable?) {
        Schedulers.trampoline().scheduleDirect(command!!)
    }
}
