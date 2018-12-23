package com.github.volfor.diary

import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.github.volfor.diary.adapters.FirebaseBindingRecyclerAdapter
import com.google.android.gms.common.SignInButton

@BindingAdapter("onSignInClick")
fun setGoogleSignInButtonClickListener(button: SignInButton, listener: View.OnClickListener) {
    button.setOnClickListener(listener)
}

@BindingAdapter("dividerSize")
fun setRecyclerItemDecoration(recycler: RecyclerView, size: Float) {
    recycler.addItemDecoration(SpaceItemDecorator(size.toInt()))
}

@BindingAdapter("firebaseOptions", "itemLayout")
fun <T> setFirebaseRecyclerViewAdapter(recycler: RecyclerView, firebaseOptions: FirebaseRecyclerOptions<T>, @LayoutRes itemLayout: Int) {
    if (recycler.adapter == null) {
        val adapter = FirebaseBindingRecyclerAdapter(firebaseOptions, itemLayout)
        recycler.adapter = adapter
    }
}