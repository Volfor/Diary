package com.github.volfor.diary

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.gms.common.SignInButton

@BindingAdapter("onSignInClick")
fun setGoogleSignInButtonClickListener(button: SignInButton, listener: View.OnClickListener) {
    button.setOnClickListener(listener)
}