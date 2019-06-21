package com.yemeksepeti.challenge.activities

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import androidx.appcompat.app.ActionBar
import com.github.johnpersano.supertoasts.library.Style
import com.github.johnpersano.supertoasts.library.SuperActivityToast
import com.raventech.fujibas.interfaces.ApiClient
import com.raventech.fujibas.interfaces.ResponsibleAPI
import com.yemeksepeti.challenge.R
import com.yemeksepeti.challenge.application.YemekApp.Companion.addFragment
import com.yemeksepeti.challenge.fragments.UserList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        addFragment(supportFragmentManager, UserList(), false, "UserList")
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

        //getUsers()
    }

    private fun getUsers() {
        ApiClient.getClient(this)
            .create(ResponsibleAPI::class.java)
            .getUsers()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (response != null) {


                    } else
                        throw Throwable(getString(R.string.responserror))


                },
                {
                    SuperActivityToast.create(this, Style(), Style.TYPE_STANDARD)
                        .setText(it.message)
                        .setDuration(Style.DURATION_SHORT)
                        .setFrame(Style.FRAME_LOLLIPOP)
                        .setColor(resources.getColor(R.color.colorAccent))
                        .setAnimations(Style.ANIMATIONS_POP).show()

                }
            )
    }


}
