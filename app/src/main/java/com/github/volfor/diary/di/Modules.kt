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
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        CoroutineContextHolderImpl(
            main = Dispatchers.Main,
            io = Dispatchers.IO,
            bg = Dispatchers.Default
        ) as CoroutineContextHolder
    }
}

val viewModelsModule = module {
    viewModel { AppViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { TravelListViewModel(get(), get()) }
    viewModel { TravelCreateViewModel(get(), get()) }
    viewModel { TravelViewModel(get(), get()) }
}

val firebaseModule = module {
    single { FirebaseDatabase.getInstance() }
}

val repositoriesModule = module {
    single { UserRepository(get(), get()) }
    single { TravelsRepository(get(), get()) }
}