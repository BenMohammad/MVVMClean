package com.mvvmclean.myapplication.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.common.truth.Truth
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.withTimeout


class LiveDataValueCapture<T> {

    private val _values = mutableListOf<T?>()
    val values: List<T?>
        get() = _values

    val channel = Channel<T?>(Channel.UNLIMITED)

    fun addValue(value: T?) {
        _values += value
        channel.offer(value)
    }

    suspend fun assertSendValues(timeout: Long, vararg expected: T?) {
        val expectedList = expected.asList()
        if(values == expectedList) {
            return
        }

        try {
            withTimeout(timeout) {
                for(value in channel) {
                    if(values == expectedList) {
                        return@withTimeout
                    }
                }
            }
        }catch (ex: TimeoutCancellationException) {
            Truth.assertThat(values).isEqualTo(expectedList)
        }
    }
}


inline fun <T> LiveData<T>.captureValues(block: LiveDataValueCapture<T>.() -> Unit) {
    val capture = LiveDataValueCapture<T>()
    val observer = Observer<T> {
        capture.addValue(it)
    }
    observeForever(observer)
    capture.block()
    removeObserver(observer)
}

fun <T> LiveData<T>.getValueForTest(): T? {
    var value: T? = null
    var observer = Observer<T> {
        value = it
    }
    observeForever(observer)
    removeObserver(observer)
    return value
}