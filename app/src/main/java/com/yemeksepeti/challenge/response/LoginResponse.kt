package com.raventech.fujibas.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.yemeksepeti.challenge.response.BaseResponse

class LoginResponse: BaseResponse() {
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("accessToken")
    @Expose
    var accessToken: String? = null
    @SerializedName("tokenType")
    @Expose
    var tokenType: String? = null

}