package com.softstark.hackernews.core.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.softstark.hackernews.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            MainActivity.start(this)
            finish()
        }, DELAY_IN_IN_TRANSITION)
    }

    companion object {
        private const val DELAY_IN_IN_TRANSITION = 2000L
    }
}