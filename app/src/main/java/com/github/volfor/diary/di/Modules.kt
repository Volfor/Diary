package com.github.volfor.diary.di

import com.github.volfor.diary.screens.login.LoginViewModel
import com.github.volfor.diary.screens.main.MainViewModel
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val viewModelsModule = Kodein.Module("View Models") {
    bind<ViewModelsFactory>() with singleton { ViewModelsFactory(kodein.direct) }

    bind<MainViewModel>() with provider { MainViewModel() }
    bind<LoginViewModel>() with provider { LoginViewModel() }
}