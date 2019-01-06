package com.github.volfor.diary.app

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
        /*  // TODO: enable when appcompat:1.1.0-alpha02 will came out
            if (LeakCanary.isInAnalyzerProcess(this)) return
            LeakCanary.install(this)
         */

        setupLogging()
    }

    private fun setupLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}