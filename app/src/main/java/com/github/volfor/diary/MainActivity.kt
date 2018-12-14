package com.github.volfor.diary

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.github.volfor.diary.base.BaseBoundVmActivity
import com.github.volfor.diary.databinding.ActivityMainBinding
import com.github.volfor.diary.extensions.observeEvent

class MainActivity : BaseBoundVmActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main,
    MainViewModel::class
) {
    sealed class Event {
        data class Toast(val message: String) : Event()
        data class Dialog(val message: String) : Event()
    }

    override fun initObservers() {
        vm.uiEvents.observeEvent(this) {
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
