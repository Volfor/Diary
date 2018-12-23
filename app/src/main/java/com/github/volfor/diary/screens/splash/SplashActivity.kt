package com.github.volfor.diary.screens.splash

import android.os.Bundle
import com.github.volfor.diary.base.BaseActivity
import com.github.volfor.diary.extensions.startActivityAndFinish
import com.github.volfor.diary.screens.login.LoginActivity
import com.github.volfor.diary.screens.travels.TravelsActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = FirebaseAuth.getInstance().currentUser
        val screen = if (user != null) TravelsActivity::class else LoginActivity::class

        startActivityAndFinish(screen)
    }
}