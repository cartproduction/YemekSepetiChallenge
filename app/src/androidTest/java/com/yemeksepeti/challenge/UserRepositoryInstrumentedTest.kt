package com.yemeksepeti.challenge

import android.content.Context
import androidx.annotation.NonNull
import androidx.lifecycle.LifecycleOwner
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.yemeksepeti.challenge.models.UserDetailsModel
import com.yemeksepeti.challenge.models.UserModel
import com.yemeksepeti.challenge.repository.UserRepository
import com.yemeksepeti.challenge.response.UserListResponse
import com.yemeksepeti.challenge.viewmodel.UserViewModel
import androidx.lifecycle.Observer
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import retrofit2.Retrofit
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class UserRepositoryInstrumentedTest {

    private lateinit var lifecycleOwner: LifecycleOwner
    private lateinit var userRepository: UserRepository
    private lateinit var userViewModel: UserViewModel
    private lateinit var userListResponse: UserListResponse
    private lateinit var userDetailsModel: UserDetailsModel
    private lateinit var instrumentationContext: Context
    private var users: ArrayList<UserModel> = object : ArrayList<UserModel>(){
        init {
            add(UserModel("user134325432", R.drawable.girl, "Ayşe", "Fatma", "aysefatma@mail.com"))
            add(UserModel("user123554351", R.drawable.man, "Hasan", "Aslan", "hasanaslan@mail.com"))
            add(UserModel("user113425433", R.drawable.boy, "Kemal", "Taşkıran", "kemaltaskiran@mail.com"))
            add( UserModel("user106780255", R.drawable.girl, "Pelin", "Ertürk", "pelinerturk@mail.com"))

        }
    }
    @Before
    @Throws(Exception::class)
    fun setUp() {

        instrumentationContext = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().context
        userRepository = UserRepository()
        userViewModel = UserViewModel()
        userListResponse = UserListResponse()
        userDetailsModel = UserDetailsModel("",0,"","",Date(),"","","")
        setUpRxSchedulers()
    }

    @BeforeClass
    fun setUpRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.yemeksepeti.challenge", appContext.packageName)
    }

    @Test
    fun testLoginWithGetAllUser() {

        userListResponse.users = users
        userViewModel.userList.postValue(userListResponse)
        assertEquals(users, userViewModel.userList.value)
    }

    @Test
    fun testGetUserByID() {


        userViewModel.userDetails.postValue(userDetailsModel)
        assertEquals(userDetailsModel, userViewModel.userDetails.value)


    }
}
