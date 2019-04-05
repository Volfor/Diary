package com.github.volfor.diary.screens.login

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.github.volfor.diary.R
import com.github.volfor.diary.base.BaseBoundVmFragment
import com.github.volfor.diary.databinding.FragmentLoginBinding
import com.github.volfor.diary.extensions.toast
import com.github.volfor.diary.livedata.ViewAction
import com.github.volfor.diary.livedata.observeEvent
import com.github.volfor.diary.screens.login.LoginFragmentDirections as Directions

class LoginFragment : BaseBoundVmFragment<FragmentLoginBinding, LoginViewModel>(
    R.layout.fragment_login,
    LoginViewModel::class
) {
    sealed class Event : ViewAction {
        data class Login(val providers: List<AuthUI.IdpConfig>) : Event()
        object Home : Event()
        object Error : Event()
    }

    override fun initObservers() {
        vm.viewAction.observeEvent(this) {
            when (it) {
                is Event.Login -> startLoginFlow(it.providers)
                is Event.Home -> findNavController().navigate(Directions.actionOpenTravelList())
                is Event.Error -> toast("Login error")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!vm.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun startLoginFlow(providers: List<AuthUI.IdpConfig>) {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(), RC_AUTH
        )
    }

    companion object {
        const val RC_AUTH = 1324
    }
}
