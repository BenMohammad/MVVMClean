package com.mvvmclean.viewmodels.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel: ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob()
    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }
}