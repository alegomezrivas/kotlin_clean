package com.softstark.hackernews.core.injection.modules

import android.app.Application
import com.softstark.hackernews.cache.NewsCacheImp
import com.softstark.hackernews.cache.db.NewsDatabase
import com.softstark.hackernews.data.repository.NewsCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun provideNewsDataBase(application: Application): NewsDatabase {
        return NewsDatabase.getInstance(application)
    }

    @Provides
    @Singleton
    fun provideNewsCache(newsCacheImp: NewsCacheImp): NewsCache {
        return newsCacheImp
    }
}