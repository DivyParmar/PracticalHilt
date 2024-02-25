package com.practical.divy.api

sealed class ApiResult<out T> {
    object Loading : ApiResult<Nothing>()
    data class Success<out T>(val response: T, val message: String) : ApiResult<T>()
    data class Failure(val errorMessage: String, val isException: Boolean = false) : ApiResult<Nothing>()
    object NoInternet : ApiResult<Nothing>()
    object SessionExpired : ApiResult<Nothing>()
}
