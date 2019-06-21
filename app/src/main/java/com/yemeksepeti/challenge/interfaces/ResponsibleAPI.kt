package com.raventech.fujibas.interfaces

import com.raventech.fujibas.models.*
import com.yemeksepeti.challenge.models.UserDetailsModel
import com.yemeksepeti.challenge.models.UserModel
import com.yemeksepeti.challenge.response.BaseResponse
import com.yemeksepeti.challenge.response.UserListResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ResponsibleAPI {

    @POST("users/login")
    fun login(@Body model: LoginModel): Observable<LoginResponse>

    @GET("users/list")
    fun getUsers(): Observable<UserListResponse>

    @GET("users/{id}")
    fun getUserByID(@Path("id") id : String ): Observable<UserDetailsModel>

    @POST("users/info")
    fun saveUserInfo(@Body model: UserDetailsModel): Observable<BaseResponse>
}