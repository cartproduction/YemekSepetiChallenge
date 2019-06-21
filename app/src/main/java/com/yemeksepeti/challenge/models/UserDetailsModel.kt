package com.yemeksepeti.challenge.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class UserDetailsModel(userId: String, profilImage: Int, firstName: String, lastName: String, birthDay: Date, phoneNumber: String, adress: String, email: String) :
    Serializable {

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
    @SerializedName("birthDay")
    @Expose
    var birthDay: Date? = birthDay
    @SerializedName("phoneNumber")
    @Expose
    var phoneNumber: String? = phoneNumber
    @SerializedName("adress")
    @Expose
    var adress: String? = adress
    @SerializedName("email")
    @Expose
    var email: String? = email

}