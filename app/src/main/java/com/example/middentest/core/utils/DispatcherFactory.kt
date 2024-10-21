package com.example.middentest.core.utils

import kotlin.coroutines.CoroutineContext

interface DispatcherFactory {
    fun getMain(): CoroutineContext
    fun getIO(): CoroutineContext
}