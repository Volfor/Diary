package com.github.volfor.diary.screens.main

import com.github.volfor.diary.base.BaseViewModel

class MainViewModel : BaseViewModel<MainActivity.Event>() {
    val message = "Vm message!"

    fun onClick() {
        _viewAction.value = MainActivity.Event.Toast("Message from ViewModel")
    }

    fun onDialog() {
        _viewAction.value = MainActivity.Event.Dialog("Dialog message from ViewModel")
    }
}