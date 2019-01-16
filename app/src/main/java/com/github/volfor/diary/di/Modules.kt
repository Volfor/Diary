package com.github.volfor.diary.di

import com.github.volfor.diary.CoroutineContextHolder
import com.github.volfor.diary.CoroutineContextHolderImpl
import com.github.volfor.diary.app.AppViewModel
import com.github.volfor.diary.repositories.TravelsRepository
import com.github.volfor.diary.repositories.UserRepository
import com.github.volfor.diary.screens.login.LoginViewModel
import com.github.volfor.diary.screens.travel.TravelViewModel
import com.github.volfor.diary.screens.travel.create.TravelCreateViewModel
import com.github.volfor.diary.screens.travel.list.TravelListViewModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val appModule = Kodein.Module("App") {
    bind<CoroutineContextHolder>() with singleton {
        CoroutineContextHolderImpl(
            main = Dispatchers.Main,
            io = Dispatchers.IO,
            bg = Dispatchers.Default
        ) as CoroutineContextHolder
    }
}

val viewModelsModule = Kodein.Module("View Models") {
    bind<ViewModelsFactory>() with singleton { ViewModelsFactory(kodein.direct) }

    bind<AppViewModel>() with provider { AppViewModel(instance()) }
    bind<LoginViewModel>() with provider { LoginViewModel(instance(), instance()) }
    bind<TravelListViewModel>() with provider { TravelListViewModel(instance(), instance()) }
    bind<TravelCreateViewModel>() with provider { TravelCreateViewModel(instance(), instance()) }
    bind<TravelViewModel>() with provider { TravelViewModel(instance(), instance()) }
}

val firebaseModule = Kodein.Module("Firebase") {
    bind<FirebaseDatabase>() with singleton { FirebaseDatabase.getInstance() }
}

val repositoriesModule = Kodein.Module("Repositories") {
    bind<UserRepository>() with singleton { UserRepository(instance(), instance()) }
    bind<TravelsRepository>() with singleton { TravelsRepository(instance(), instance()) }
}