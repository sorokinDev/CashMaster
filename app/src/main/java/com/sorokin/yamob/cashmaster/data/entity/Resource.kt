package com.sorokin.yamob.cashmaster.data.entity

sealed class Resource<out T> {
    class Loading<out T>(val data: T?) : Resource<T>()
    data class Success<out T>(val data: T?) : Resource<T>()
    data class Failure<out T>(val data: T?, val throwable: Throwable) : Resource<T>()
}
