package com.practical.divy.api

import com.practical.divy.model.BaseResponse
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class ApiResultParser {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Flow<ApiResult<T>> {
        return flow {
            emit(ApiResult.Loading)
            try {
                val response = apiCall()
                val body = response.body()
                if (response.isSuccessful && body is BaseResponse) {
                    val data = body as BaseResponse
                    if (data.isSuccess()) {
                        emit(ApiResult.Success(body, body.message.toString()))
                    } else if (data.isSessionExpired()) {
                        emit(ApiResult.SessionExpired)
                    } else {
                        emit(ApiResult.Failure("${response.code()} ${response.message()}"))
                    }
                } else {
                    emit(ApiResult.Failure("${response.code()} ${response.message()}"))
                }
            } catch (e: Exception) {
                emit(ApiResult.Failure(e.message ?: e.toString(), true))
            }
        }.flowOn(Dispatchers.IO)
    }

}