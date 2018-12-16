package com.github.volfor.diary.screens.login

import android.app.Activity
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.github.ajalt.timberkt.d
import com.github.volfor.diary.base.BaseViewModel
import com.github.volfor.diary.screens.login.LoginActivity.Event.Home
import com.github.volfor.diary.screens.login.LoginActivity.Event.Login

class LoginViewModel : BaseViewModel<LoginActivity.Event>() {
    private val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())

    fun login() {
        _viewAction.value = Login(providers)
    }

    fun showHome() {
        _viewAction.value = Home
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        when (requestCode) {
            RC_AUTH -> {
                val response = IdpResponse.fromResultIntent(data)
                if (resultCode == Activity.RESULT_OK) {
                    showHome()
                } else {
                    // Sign in failed

                    when {
                        response == null -> // User pressed back button
                            d { "Auth error: canceled by user" }
                        response.error?.errorCode == ErrorCodes.NO_NETWORK -> // TODO: no internet
                            d { "Auth error: no internet connection" }
                        else -> // TODO: unknown error
                            d { "Auth error: unknown -- ${response.error}" }
                    }
                }
                return true
            }
        }
        return false
    }
}