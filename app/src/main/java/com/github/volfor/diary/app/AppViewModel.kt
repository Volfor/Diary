package com.github.volfor.diary.app

import com.github.volfor.diary.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth

class AppViewModel : BaseViewModel() {

    fun isUserLoggedIn() = FirebaseAuth.getInstance().currentUser != null

}