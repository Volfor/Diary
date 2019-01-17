package com.github.volfor.diary.base

import androidx.lifecycle.ViewModel
import com.github.volfor.diary.CoroutineContextHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
    ctx: CoroutineContextHolder
) : ViewModel(), CoroutineContextHolder by ctx, CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + main

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}