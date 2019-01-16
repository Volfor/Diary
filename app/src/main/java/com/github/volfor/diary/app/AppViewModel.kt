package com.github.volfor.diary.app

import com.github.volfor.diary.CoroutineContextHolder
import com.github.volfor.diary.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth

class AppViewModel(
    ctx: CoroutineContextHolder
) : BaseViewModel(ctx) {

    fun isLoggedIn() = FirebaseAuth.getInstance().currentUser != null

}