package com.yemeksepeti.challenge.viewmodel

import android.app.ProgressDialog
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.johnpersano.supertoasts.library.Style
import com.github.johnpersano.supertoasts.library.SuperActivityToast
import com.raventech.fujibas.interfaces.ApiClient
import com.raventech.fujibas.interfaces.ResponsibleAPI
import com.raventech.fujibas.models.LoginModel
import com.raventech.fujibas.models.LoginResponse
import com.yemeksepeti.challenge.models.UserDetailsModel
import com.yemeksepeti.challenge.response.UserListResponse
import io.reactivex.android.schedulers.AndroidSchedulers

class UserViewModel : ViewModel() {

    val userList: MutableLiveData<UserListResponse?> by lazy {
        MutableLiveData<UserListResponse?>()
    }

    val userDetails: MutableLiveData<UserDetailsModel?> by lazy {
        MutableLiveData<UserDetailsModel?>()
    }

}

