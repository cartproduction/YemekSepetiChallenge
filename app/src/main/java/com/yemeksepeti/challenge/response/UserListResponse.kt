package com.yemeksepeti.challenge.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.yemeksepeti.challenge.models.UserModel

class UserListResponse : BaseResponse(){

    @SerializedName("users")
    @Expose
    var users: List<UserModel>? = null

}