package com.github.volfor.diary.screens.login

import android.app.Activity
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import com.github.volfor.diary.base.BaseEventViewModel
import com.github.volfor.diary.extensions.async
import com.github.volfor.diary.repositories.UserRepository
import com.github.volfor.diary.screens.login.LoginFragment.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.rxkotlin.subscribeBy

class LoginViewModel(
    private val userRepository: UserRepository
) : BaseEventViewModel<LoginFragment.Event>() {
    private val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())

    fun login() {
        sendEvent(Event.Login(providers))
    }

    fun showHome() {
        sendEvent(Event.Home)
    }

    fun showError() {
        sendEvent(Event.Error)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        when (requestCode) {
            RC_AUTH -> {
                val response = IdpResponse.fromResultIntent(data)
                if (resultCode == Activity.RESULT_OK) {
                    // Sign in successful
                    saveUser(FirebaseAuth.getInstance().currentUser)
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

    private fun saveUser(user: FirebaseUser?) {
        if (user == null) {
            showError()
            return
        }

        userRepository.create(user)
            .async()
            .subscribeBy(
                onNext = { successful ->
                    if (successful) {
                        showHome()
                    } else {
                        showError()
                    }
                },
                onError = {
                    e(it)
                    showError()
                }
            ).add()
    }
}