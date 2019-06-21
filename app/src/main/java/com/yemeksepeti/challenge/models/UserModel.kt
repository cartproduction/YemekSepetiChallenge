package com.yemeksepeti.challenge.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserModel(userId: String,profilImage: Int, firstName: String, lastName: String, email: String) : Serializable {

    @SerializedName("userId")
    @Expose
    var userId: String? = userId
    @SerializedName("profilImage")
    @Expose
    var profilImage: Int? = profilImage
    @SerializedName("firstName")
    @Expose
    var firstName: String? = firstName
    @SerializedName("lastName")
    @Expose
    var lastName: String? = lastName
    @SerializedName("email")
    @Expose
    var email: String? = email

}