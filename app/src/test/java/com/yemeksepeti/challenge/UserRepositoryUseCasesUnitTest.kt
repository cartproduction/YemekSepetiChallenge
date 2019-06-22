package com.yemeksepeti.challenge

import android.content.Context
import com.yemeksepeti.challenge.models.UserDetailsModel
import com.yemeksepeti.challenge.models.UserModel
import com.yemeksepeti.challenge.repository.UserRepository
import com.yemeksepeti.challenge.response.UserListResponse
import com.yemeksepeti.challenge.viewmodel.UserViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import retrofit2.Retrofit
import java.util.*


class UserRepositoryUseCasesUnitTest {

    private lateinit var userRepository: UserRepository
    private lateinit var userViewModel: UserViewModel
    private lateinit var userDetailsModel: UserDetailsModel
    private lateinit var userListResponse: UserListResponse
    private lateinit var apiService: Retrofit
    private var users: ArrayList<UserModel> = object : ArrayList<UserModel>(){
        init {
            add(UserModel("user134325432", R.drawable.girl, "Ayşe", "Fatma", "aysefatma@mail.com"))
            add(UserModel("user123554351", R.drawable.man, "Hasan", "Aslan", "hasanaslan@mail.com"))
            add(UserModel("user113425433", R.drawable.boy, "Kemal", "Taşkıran", "kemaltaskiran@mail.com"))
            add(UserModel("user106780255", R.drawable.girl, "Pelin", "Ertürk", "pelinerturk@mail.com"))

        }
    }
    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    @Throws(Exception::class)
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        userRepository = UserRepository()
        userViewModel = UserViewModel()
        userListResponse = UserListResponse()
        userDetailsModel = mock(UserDetailsModel::class.java)
    }


    @Test
    fun testLoginWithGetAllUser() {

        userListResponse.users = users
        userRepository.loginWithGetAllUser("","", mock(Retrofit::class.java),userViewModel)
        Assert.assertTrue(userViewModel.userList.value == userListResponse)
    }

    @Test
    fun testGetUserByID() {

        userRepository.getUserByID(userDetailsModel,mock(Retrofit::class.java),userViewModel)
        Assert.assertTrue(userViewModel.userDetails.value == userDetailsModel)

    }


}