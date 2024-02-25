package com.practical.divy.model

import com.google.gson.annotations.SerializedName

data class DogResponse(
    @SerializedName("data")
    val data: String? = null,
) : BaseResponse()
