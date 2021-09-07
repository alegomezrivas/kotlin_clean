package com.softstark.hackernews.core.injection.modules

import com.softstark.hackernews.BuildConfig
import com.softstark.hackernews.data.repository.NewsRemote
import com.softstark.hackernews.remote.NewsRemoteImp
import com.softstark.hackernews.remote.services.NewsService
import com.softstark.hackernews.remote.services.NewsServiceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideMovieRemote(NewsRemoteImp: NewsRemoteImp): NewsRemote {
        return NewsRemoteImp
    }

    @Provides
    @Singleton
    fun provideNewservice(): NewsService {
        return NewsServiceFactory.create(
            BuildConfig.DEBUG,
            BuildConfig.BASE_URL
        )
    }
}