package com.github.volfor.diary.app

import android.app.Application
import com.github.ajalt.timberkt.Timber
import com.github.volfor.diary.BuildConfig
import com.github.volfor.diary.di.appModule
import com.github.volfor.diary.di.firebaseModule
import com.github.volfor.diary.di.repositoriesModule
import com.github.volfor.diary.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        insertKoin()

        /*  // TODO: enable when appcompat:1.1.0-alpha02 will came out
            if (LeakCanary.isInAnalyzerProcess(this)) return
            LeakCanary.install(this)
         */

        setupLogging()
    }

    private fun insertKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                viewModelsModule,
                firebaseModule,
                repositoriesModule
            )
        }
    }

    private fun setupLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}