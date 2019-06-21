package com.yemeksepeti.challenge.activities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.appus.splash.Splash
import com.github.johnpersano.supertoasts.library.Style
import com.github.johnpersano.supertoasts.library.SuperActivityToast
import com.raventech.fujibas.interfaces.ApiClient
import com.raventech.fujibas.interfaces.ResponsibleAPI
import com.solvepark.yemekgelircustomer.UserProfile
import com.yemeksepeti.challenge.fragments.Login
import com.yemeksepeti.challenge.R
import com.yemeksepeti.challenge.application.YemekApp.Companion.addFragment
import com.yemeksepeti.challenge.models.UserDetailsModel
import com.yemeksepeti.challenge.models.UserModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_user.*
import java.util.*


class UserProfileActivity : AppCompatActivity() {

    lateinit var user : UserModel
    lateinit var userDetails : UserDetailsModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        user =  intent.extras.get("user") as UserModel

        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x

        val drawable = resources.getDrawable(R.drawable.banabi)
        val bitmap = (drawable as BitmapDrawable).bitmap
        val newDrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, width/2, width/3, true))

        val splash = Splash.Builder(this, supportActionBar)
        splash.setBackgroundColor(resources.getColor(R.color.white))
        splash.setSplashImageColor(resources.getColor(R.color.colorAccent))
        splash.setSplashImage(newDrawable)
        splash.perform()
        initToolbar()

        val fragment = UserProfile()
        val bundle =  Bundle()
        val dummyUser = UserDetailsModel(user.userId!!,user.profilImage!!, user.firstName!!, user.lastName!!, Date(),"+9055********", "Balmumcu Mh. Kırçiçeği Sk.",user.email!!)
        bundle.putSerializable("dummyUser", dummyUser)
        fragment.arguments = bundle
        addFragment(supportFragmentManager, fragment, false, "UserProfile")



    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar

        // Specify that tabs should be displayed in the action bar.
        actionBar!!.setDisplayHomeAsUpEnabled(false)
        actionBar.setDisplayShowTitleEnabled(false)
        val mInflater = LayoutInflater.from(this)
        val mCustomView = mInflater.inflate(R.layout.toolbar_layout, null)
        val params = ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER)



        actionBar.setCustomView(mCustomView, params)
        actionBar.setDisplayShowCustomEnabled(true)
        actionBar.setStackedBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorAccent)))
        actionBar.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorAccent)))
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)

    }

}
