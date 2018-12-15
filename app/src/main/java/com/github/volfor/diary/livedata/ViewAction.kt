package com.github.volfor.diary.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

interface ViewAction

inline fun <TEvent : ViewAction> LiveData<TEvent>.observeEvent(
    owner: LifecycleOwner,
    crossinline onEventUnhandledContent: (TEvent) -> Unit
) {
    observe(owner, Observer { it?.let(onEventUnhandledContent) })
}