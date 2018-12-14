package com.github.volfor.diary.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.DKodein
import org.kodein.di.TT

class ViewModelsFactory(
    private val injector: DKodein
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return injector.Instance(TT(modelClass))
    }
}