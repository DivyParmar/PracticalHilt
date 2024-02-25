package com.practical.divy.api

import com.practical.divy.model.DogResponse
import com.practical.divy.utils.AppConstants
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET(AppConstants.RANDOM_URL)
    suspend fun getDog(): Response<DogResponse>
}