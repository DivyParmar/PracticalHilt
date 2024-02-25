package com.practical.divy.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @Expose
    @SerializedName("status")
    var status: String? = "",
    @Expose
    @SerializedName("message")
    var message: String? = ""
) {
    fun isSuccess() : Boolean{
        return status == "success"
    }

    fun isSessionExpired() : Boolean{
        return status == "sessionExpired"
    }
}