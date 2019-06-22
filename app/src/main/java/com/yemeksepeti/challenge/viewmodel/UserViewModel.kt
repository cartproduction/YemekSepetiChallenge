package com.yemeksepeti.challenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yemeksepeti.challenge.models.UserDetailsModel
import com.yemeksepeti.challenge.response.UserListResponse

class UserViewModel : ViewModel() {

    val userList: MutableLiveData<UserListResponse?> by lazy {
        MutableLiveData<UserListResponse?>()
    }

    val userDetails: MutableLiveData<UserDetailsModel?> by lazy {
        MutableLiveData<UserDetailsModel?>()
    }

}

