package com.softstark.hackernews.core.injection.modules

import androidx.annotation.UiThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresentationModule {

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UiThread): UiThread {
        return uiThread
    }
}