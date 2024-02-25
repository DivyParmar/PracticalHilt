package com.practical.divy.repository

import com.practical.divy.api.ApiResult
import com.practical.divy.api.ApiResultParser
import com.practical.divy.model.DogResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class ApiRepositoryImpl @Inject constructor(private val apiRepository: ApiRepository) :
    ApiResultParser() {

    suspend fun getDog(): Flow<ApiResult<DogResponse>> {
        return safeApiCall {
            apiRepository.getDog()
        }
    }
}