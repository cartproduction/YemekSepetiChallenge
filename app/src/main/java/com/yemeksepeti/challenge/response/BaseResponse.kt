package com.yemeksepeti.challenge.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseResponse {

    @SerializedName("Success")
    @Expose
    var success: Boolean? = null
    @SerializedName("ErrorCode")
    @Expose
    var errorCode: Int? = null
    @SerializedName("ErrorMessage")
    @Expose
    var errorMessage: String? = null

}