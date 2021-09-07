package com.softstark.hackernews.core.injection.modules

import com.softstark.hackernews.data.NewsDataRepository
import com.softstark.hackernews.domain.SingleUseCase
import com.softstark.hackernews.domain.entities.News
import com.softstark.hackernews.domain.repositories.NewsRepository
import com.softstark.hackernews.domain.usecases.GetNewsListUseCase
import com.softstark.hackernews.domain.usecases.RemoveNewsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideNewsRepository(NewsDataRepository: NewsDataRepository): NewsRepository {
        return NewsDataRepository
    }

    @Provides
    @Singleton
    fun provideGetNewsListUseCase(getNewsListUseCase: GetNewsListUseCase): SingleUseCase<Void, List<News>> {
        return getNewsListUseCase
    }

    @Provides
    @Singleton
    fun provideRemoveNewsUseCase(removeNewsUseCase: RemoveNewsUseCase): RemoveNewsUseCase {
        return removeNewsUseCase
    }
}
