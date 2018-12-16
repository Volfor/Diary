package com.github.volfor.diary.screens.main

import com.github.volfor.diary.base.BaseViewModel
import com.github.volfor.diary.screens.main.MainActivity.Event

class MainViewModel : BaseViewModel<MainActivity.Event>() {
    val message = "Vm message!"

    fun onClick() {
        _viewAction.value = Event.Toast("Message from ViewModel")
    }

    fun onDialog() {
        _viewAction.value = Event.Dialog("Dialog message from ViewModel")
    }

    fun logout() {
        _viewAction.value = Event.Logout
    }
}