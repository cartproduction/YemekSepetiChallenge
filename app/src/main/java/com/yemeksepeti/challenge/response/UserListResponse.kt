package com.yemeksepeti.challenge.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.yemeksepeti.challenge.models.UserModel
import java.io.Serializable

class UserListResponse : BaseResponse(), Serializable {

    @SerializedName("users")
    @Expose
    var users: List<UserModel>? = null

}