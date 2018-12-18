package com.github.volfor.diary.di

import com.github.volfor.diary.repositories.UserRepository
import com.github.volfor.diary.screens.login.LoginViewModel
import com.github.volfor.diary.screens.main.MainViewModel
import com.google.firebase.database.FirebaseDatabase
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val viewModelsModule = Kodein.Module("View Models") {
    bind<ViewModelsFactory>() with singleton { ViewModelsFactory(kodein.direct) }

    bind<MainViewModel>() with provider { MainViewModel() }
    bind<LoginViewModel>() with provider { LoginViewModel(instance()) }
}

val firebaseModule = Kodein.Module("Firebase") {
    bind<FirebaseDatabase>() with singleton { FirebaseDatabase.getInstance() }
}

val repositoriesModule = Kodein.Module("Repositories") {
    bind<UserRepository>() with singleton { UserRepository(instance()) }
}