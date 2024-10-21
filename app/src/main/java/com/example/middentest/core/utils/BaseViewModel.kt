package com.example.middentest.core.utils

import androidx.lifecycle.ViewModel
import com.example.proyecto2.utils.DispatcherFactory
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import org.koin.core.component.KoinComponent

abstract class BaseViewModel(
    private val dispatcherFactory: DispatcherFactory
) : ViewModel(), CoroutineScope, KoinComponent {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatcherFactory.getMain() + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}