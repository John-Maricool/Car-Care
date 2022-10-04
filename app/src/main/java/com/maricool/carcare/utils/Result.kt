package com.maricool.carcare.utils

sealed class Result<out R> {
    data class Success<out T>(val data: T? = null, val msg: String? = null) : Result<T>()
    class ErrorResult(val error: Error) : Result<Nothing>()
    class LoadingState(val isLoading: Boolean) : Result<Nothing>()
}

interface Error {
    val error: Int
}

class ExceptionError(override val error: Int): Error
class InternetError(override val error: Int) : Error
class EmptyListError(override val error: Int): Error
class ServerError(override val error: Int) : Error

