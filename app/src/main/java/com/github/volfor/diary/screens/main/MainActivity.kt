package com.github.volfor.diary.screens.main

import android.widget.Toast
import com.github.volfor.diary.R
import com.github.volfor.diary.base.BaseBoundVmActivity
import com.github.volfor.diary.databinding.ActivityMainBinding
import com.github.volfor.diary.extensions.observeEvent

class MainActivity : BaseBoundVmActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main,
    MainViewModel::class
) {
    sealed class Event {
        data class Toast(val message: String) : Event()
    }

    override fun initObservers() {
        vm.uiEvents.observeEvent(this) {
            when (it) {
                is Event.Toast -> Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
