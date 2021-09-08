package com.softstark.hackernews.core.injection

import android.app.Application
import com.softstark.hackernews.core.injection.modules.*
import com.softstark.hackernews.core.ui.NewsListActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        CacheModule::class,
        DomainModule::class,
        RemoteModule::class,
        PresentationModule::class,
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(newsListActivity: NewsListActivity)
}
