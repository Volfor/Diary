package com.github.volfor.diary.application

import android.app.Application
import com.github.ajalt.timberkt.Timber
import com.github.volfor.diary.BuildConfig
import com.github.volfor.diary.di.firebaseModule
import com.github.volfor.diary.di.repositoriesModule
import com.github.volfor.diary.di.viewModelsModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class App : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(viewModelsModule)
        import(firebaseModule)
        import(repositoriesModule)
    }

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