package com.github.volfor.diary

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.SignInButton

@BindingAdapter("onSignInClick")
fun setGoogleSignInButtonClickListener(button: SignInButton, listener: View.OnClickListener) {
    button.setOnClickListener(listener)
}

@BindingAdapter("dividerSize")
fun setRecyclerItemDecoration(recycler: RecyclerView, size: Float) {
    recycler.addItemDecoration(SpaceItemDecorator(size.toInt()))
}