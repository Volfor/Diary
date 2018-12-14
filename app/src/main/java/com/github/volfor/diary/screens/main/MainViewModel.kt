package com.github.volfor.diary.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.volfor.diary.extensions.Event

class MainViewModel : ViewModel() {

    private val _uiEvents = MutableLiveData<Event<MainActivity.Event>>()

    val uiEvents: LiveData<Event<MainActivity.Event>>
        get() = _uiEvents

    val message = "Vm message!"

    fun onClick() {
        _uiEvents.value = Event(MainActivity.Event.Toast("Message from vm"))
    }
}