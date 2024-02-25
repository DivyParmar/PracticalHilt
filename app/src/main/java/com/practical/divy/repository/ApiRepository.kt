package com.practical.divy.repository

import com.practical.divy.api.ApiInterface
import javax.inject.Inject


class ApiRepository @Inject constructor(private val apiInterface: ApiInterface)  {
        suspend fun getDog() = apiInterface.getDog()
}