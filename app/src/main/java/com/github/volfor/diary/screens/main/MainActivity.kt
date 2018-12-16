package com.github.volfor.diary.screens.main

import androidx.appcompat.app.AlertDialog
import com.firebase.ui.auth.AuthUI
import com.github.volfor.diary.R
import com.github.volfor.diary.base.BaseBoundVmActivity
import com.github.volfor.diary.databinding.ActivityMainBinding
import com.github.volfor.diary.livedata.ViewAction
import com.github.volfor.diary.livedata.observeEvent
import com.github.volfor.diary.screens.login.LoginActivity
import com.github.volfor.diary.startActivityAndFinish
import com.github.volfor.diary.toast

class MainActivity : BaseBoundVmActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main,
    MainViewModel::class
) {
    sealed class Event : ViewAction {
        data class Toast(val message: String) : Event()
        data class Dialog(val message: String) : Event()
        object Logout : Event()
    }

    override fun initObservers() {
        vm.viewAction.observeEvent(this) {
            when (it) {
                is Event.Toast -> toast(it.message)
                is Event.Dialog -> showDialog(it.message)
                is Event.Logout -> logout()
            }
        }
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .show()
    }

    private fun logout() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                startActivityAndFinish(LoginActivity::class)
            }
    }
}
