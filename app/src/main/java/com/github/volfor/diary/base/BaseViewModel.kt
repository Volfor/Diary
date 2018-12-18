package com.github.volfor.diary.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.volfor.diary.livedata.SingleLiveEvent
import com.github.volfor.diary.livedata.ViewAction
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

@Suppress("PropertyName")
abstract class BaseViewModel<TEvent : ViewAction> : ViewModel() {
    protected val _viewAction = SingleLiveEvent<TEvent>()

    val viewAction: LiveData<TEvent>
        get() = _viewAction

    private val disposables = CompositeDisposable()

    fun subscribe(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}