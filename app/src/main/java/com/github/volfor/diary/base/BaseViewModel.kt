package com.github.volfor.diary.base

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.volfor.diary.livedata.SingleLiveEvent
import com.github.volfor.diary.livedata.ViewAction
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo

@Suppress("PropertyName")
open class BaseViewModel<TEvent : ViewAction> : ViewModel(), Observable {
    @Transient
    private var callbacks: PropertyChangeRegistry? = null

    protected val _viewAction = SingleLiveEvent<TEvent>()

    val viewAction: LiveData<TEvent>
        get() = _viewAction

    private val disposables = CompositeDisposable()

    fun Disposable.add() = addTo(disposables)

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        synchronized(this) {
            if (callbacks == null) {
                callbacks = PropertyChangeRegistry()
            }
        }
        callbacks!!.add(callback)

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        synchronized(this) {
            if (callbacks == null) {
                return
            }
        }
        callbacks!!.remove(callback)
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    fun notifyChange() {
        synchronized(this) {
            if (callbacks == null) {
                return
            }
        }
        callbacks!!.notifyCallbacks(this, 0, null)
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with [Bindable] to generate a field in
     * `BR` to be used as `fieldId`.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    fun notifyPropertyChanged(fieldId: Int) {
        synchronized(this) {
            if (callbacks == null) {
                return
            }
        }
        callbacks!!.notifyCallbacks(this, fieldId, null)
    }
}