package com.github.volfor.diary

import kotlin.coroutines.CoroutineContext

interface CoroutineContextHolder {
    val main: CoroutineContext
    val io: CoroutineContext
    val bg: CoroutineContext
}

internal data class CoroutineContextHolderImpl(
    override val main: CoroutineContext,
    override val io: CoroutineContext,
    override val bg: CoroutineContext
) : CoroutineContextHolder