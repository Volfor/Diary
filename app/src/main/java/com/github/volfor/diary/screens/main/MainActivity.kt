package com.github.volfor.diary.screens.main

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.github.volfor.diary.R
import com.github.volfor.diary.base.BaseBoundVmActivity
import com.github.volfor.diary.databinding.ActivityMainBinding
import com.github.volfor.diary.livedata.ViewAction
import com.github.volfor.diary.livedata.observeEvent

class MainActivity : BaseBoundVmActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main,
    MainViewModel::class
) {
    sealed class Event : ViewAction {
        data class Toast(val message: String) : Event()
        data class Dialog(val message: String) : Event()
    }

    override fun initObservers() {
        vm.viewAction.observeEvent(this) {
            when (it) {
                is Event.Toast -> Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                is Event.Dialog -> showDialog(it.message)
            }
        }
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .show()
    }
}
