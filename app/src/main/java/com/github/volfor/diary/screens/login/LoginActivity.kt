package com.github.volfor.diary.screens.login

import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.github.volfor.diary.R
import com.github.volfor.diary.base.BaseBoundVmActivity
import com.github.volfor.diary.databinding.ActivityLoginBinding
import com.github.volfor.diary.livedata.ViewAction
import com.github.volfor.diary.livedata.observeEvent
import com.github.volfor.diary.screens.main.MainActivity
import com.github.volfor.diary.startActivityAndFinish

const val RC_AUTH = 1324

class LoginActivity : BaseBoundVmActivity<ActivityLoginBinding, LoginViewModel>(
    R.layout.activity_login,
    LoginViewModel::class
) {
    sealed class Event : ViewAction {
        data class Login(val providers: List<AuthUI.IdpConfig>) : Event()
        object Home : Event()
    }

    override fun initObservers() {
        vm.viewAction.observeEvent(this) {
            when (it) {
                is Event.Login -> startLoginFlow(it.providers)
                is Event.Home -> {
                    startActivityAndFinish(MainActivity::class)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!vm.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun startLoginFlow(providers: List<AuthUI.IdpConfig>) {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(), RC_AUTH
        )
    }
}