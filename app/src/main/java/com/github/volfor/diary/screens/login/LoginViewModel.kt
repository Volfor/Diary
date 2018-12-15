package com.github.volfor.diary.screens.login

import android.app.Activity
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
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
                    // val user = FirebaseAuth.getInstance().currentUser
                    showHome()
                    return true
                } else {
                    // Sign in failed. If response is null the user canceled the
                    // sign-in flow using the back button. Otherwise check
                    // response.getError().getErrorCode() and handle the error.
                }
            }
        }
        return false
    }
}