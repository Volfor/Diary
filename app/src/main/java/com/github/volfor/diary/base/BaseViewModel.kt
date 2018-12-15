package com.github.volfor.diary.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.volfor.diary.livedata.SingleLiveEvent
import com.github.volfor.diary.livedata.ViewAction

@Suppress("PropertyName")
abstract class BaseViewModel<TEvent : ViewAction> : ViewModel() {
    protected val _viewAction = SingleLiveEvent<TEvent>()

    val viewAction: LiveData<TEvent>
        get() = _viewAction
}