package com.github.volfor.diary.base

import androidx.lifecycle.LiveData
import com.github.volfor.diary.CoroutineContextHolder
import com.github.volfor.diary.livedata.SingleLiveEvent
import com.github.volfor.diary.livedata.ViewAction

open class BaseEventViewModel<TEvent : ViewAction>(
    ctx: CoroutineContextHolder
) : BaseViewModel(ctx) {

    private val _viewAction = SingleLiveEvent<TEvent>()

    val viewAction: LiveData<TEvent>
        get() = _viewAction

    fun sendEvent(event: TEvent) {
        _viewAction.postValue(event)
    }
}