package com.softstark.hackernews.core

import android.app.Application
import com.softstark.hackernews.core.injection.AppComponent

class MainApplication : Application() {

    init {
        instance = this
    }

    var appComponent: AppComponent = null!!
//        AppComponent.Builder.application(this).build()

    companion object {
        var instance: MainApplication? = null
        fun appComponent(): AppComponent {
            return instance!!.appComponent
        }
    }
}
