package com.antonprokopov.core.data

sealed class Resource<T> {

    companion object {
        fun <T> newLoading(): Resource<T> = Loading()
        fun <T> newSuccess(data: T): Resource<T> = Success(data)
        fun <T> newError(errorDesc: ErrorDesc): Resource<T> = Error(errorDesc)
        fun <T> newError(message: String? = null): Resource<T> = newError(ErrorDesc(message = message))
    }

    class Loading<T> internal constructor() : Resource<T>()
    data class Error<T> internal constructor(val desc: ErrorDesc) : Resource<T>()
    data class Success<T> internal constructor(val data: T) : Resource<T>()

    data class ErrorDesc(
        val message: String? = null,
        val messageId: Int? = null,
        val errorCode: Int? = null,
        val isNetwork: Boolean? = false
    )
}
