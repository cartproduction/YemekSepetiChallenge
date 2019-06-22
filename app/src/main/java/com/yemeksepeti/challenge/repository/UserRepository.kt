package com.yemeksepeti.challenge.repository

import com.raventech.fujibas.interfaces.ResponsibleAPI
import com.raventech.fujibas.models.LoginModel
import com.yemeksepeti.challenge.R
import com.yemeksepeti.challenge.application.YemekApp.Companion.TOKEN
import com.yemeksepeti.challenge.models.UserDetailsModel
import com.yemeksepeti.challenge.models.UserModel
import com.yemeksepeti.challenge.response.UserListResponse
import com.yemeksepeti.challenge.viewmodel.UserViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import java.util.*

class UserRepository {

    private var userList: ArrayList<UserModel> = ArrayList()
    private var testResponse = UserListResponse()
    fun loginWithGetAllUser(email : String, password : String, apiService: Retrofit,viewModel: UserViewModel) {

        val model = LoginModel()
        model.email = email
        model.password = password

        apiService.create(ResponsibleAPI::class.java)
            .login(model)
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { response ->

                if (response != null) {
                    TOKEN = response.accessToken

                } else
                    throw Throwable("responserror")

                return@flatMap apiService
                    .create(ResponsibleAPI::class.java)
                    .getUsers()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            .subscribe(
                { response ->

                    if (response != null) {

                        val user1 = UserModel("user134325432", R.drawable.girl, "Ayşe", "Fatma", "aysefatma@mail.com")
                        val user2 = UserModel("user123554351", R.drawable.man, "Hasan", "Aslan", "hasanaslan@mail.com")
                        val user3 =
                            UserModel("user113425433", R.drawable.boy, "Kemal", "Taşkıran", "kemaltaskiran@mail.com")
                        val user4 =
                            UserModel("user106780255", R.drawable.girl, "Pelin", "Ertürk", "pelinerturk@mail.com")

                        userList.clear()
                        userList.add(user1)
                        userList.add(user2)
                        userList.add(user3)
                        userList.add(user4)

                        testResponse.users = userList

                        viewModel.userList.value = testResponse

                    } else
                        throw Throwable("responserror")


                },
                {

                    /*SuperActivityToast.create(
                        context,
                        Style(),
                        Style.TYPE_STANDARD
                    )
                        .setText(context.getString(R.string.testscenario))
                        .setDuration(Style.DURATION_SHORT)
                        .setFrame(Style.FRAME_LOLLIPOP)
                        .setColor(context.resources.getColor(R.color.colorAccent))
                        .setAnimations(Style.ANIMATIONS_POP).show()*/
                    val user1 = UserModel("user134325432", R.drawable.girl, "Ayşe", "Fatma", "aysefatma@mail.com")
                    val user2 = UserModel("user123554351", R.drawable.man, "Hasan", "Aslan", "hasanaslan@mail.com")
                    val user3 =
                        UserModel("user113425433", R.drawable.boy, "Kemal", "Taşkıran", "kemaltaskiran@mail.com")
                    val user4 =
                        UserModel("user106780255", R.drawable.girl, "Pelin", "Ertürk", "pelinerturk@mail.com")

                    userList.clear()
                    userList.add(user1)
                    userList.add(user2)
                    userList.add(user3)
                    userList.add(user4)
                    testResponse.users = userList

                    viewModel.userList.value = testResponse
                }
            )
    }

    fun getUserByID(user : UserDetailsModel,apiService: Retrofit,viewModel: UserViewModel) {


        apiService
            .create(ResponsibleAPI::class.java)
            .getUserByID(user.userId!!)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (response != null) {
                        viewModel.userDetails.value = response

                    } else
                        throw Throwable("responserror")


                },
                {
                    /*SuperActivityToast.create(context, Style(), Style.TYPE_STANDARD)
                        .setText(it.message)
                        .setDuration(Style.DURATION_SHORT)
                        .setFrame(Style.FRAME_LOLLIPOP)
                        .setColor(context.resources.getColor(R.color.colorAccent))
                        .setAnimations(Style.ANIMATIONS_POP).show()*/
                    viewModel.userDetails.value = user

                }
            )
    }

}