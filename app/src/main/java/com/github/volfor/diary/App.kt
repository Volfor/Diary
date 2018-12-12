package com.github.volfor.diary

import android.app.Application
import com.github.ajalt.timberkt.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupLogging()
    }

    private fun setupLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}