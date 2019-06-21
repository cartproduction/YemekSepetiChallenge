package com.yemeksepeti.challenge.fragments

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.github.johnpersano.supertoasts.library.Style
import com.github.johnpersano.supertoasts.library.SuperActivityToast
import io.reactivex.android.schedulers.AndroidSchedulers
import android.app.Activity
import android.os.Handler
import android.view.*
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.raventech.fujibas.interfaces.ApiClient
import com.raventech.fujibas.interfaces.ResponsibleAPI
import com.raventech.fujibas.models.LoginModel
import com.yemeksepeti.challenge.R
import com.yemeksepeti.challenge.activities.MainActivity
import com.yemeksepeti.challenge.application.YemekApp.Companion.TOKEN
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.toolbar_layout.*


//1
class Login : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
      setHasOptionsMenu(true)
      var drawable = ResourcesCompat.getDrawable(
          resources,
          R.mipmap.back,
          null
      )
      drawable = DrawableCompat.wrap(drawable!!)
      requireActivity().toolbar.navigationIcon = drawable
    requireActivity().apptitle_text.text = resources.getString(R.string.logintext)
    requireActivity().apptitle_text.visibility = View.VISIBLE
    requireActivity().imageView18.visibility = View.GONE

    return inflater.inflate(R.layout.fragment_login, container, false)
  }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {

            android.R.id.home -> {
                requireActivity().onBackPressed()
            }


            else ->
                super.onOptionsItemSelected(item)
        }

        return true

    }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

      view.isFocusableInTouchMode = true
      view.requestFocus()
      view.setOnKeyListener { v, keyCode, event ->
          if( keyCode == KeyEvent.KEYCODE_BACK)
          {
              requireActivity().finish()
              return@setOnKeyListener true
          }
          return@setOnKeyListener false

      }

    btnSignIn.setOnClickListener(View.OnClickListener {
      val email = loginEmail.text.toString()
      val password = loginPass.text.toString()

      if (email.isEmpty()) {
        SuperActivityToast.create(
            requireContext(),
            Style(),
            Style.TYPE_STANDARD
        )
                .setText(getString(R.string.invalidemail))
                .setDuration(Style.DURATION_SHORT)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setColor(resources.getColor(R.color.colorAccent))
                .setAnimations(Style.ANIMATIONS_POP).show()
        loginEmail.requestFocus()
        return@OnClickListener
      }

      if (!email.contains('@')&&!email.contains('.')) {
          SuperActivityToast.create(
              requireContext(),
              Style(),
              Style.TYPE_STANDARD
          )
                  .setText(getString(R.string.invalidemail))
                  .setDuration(Style.DURATION_SHORT)
                  .setFrame(Style.FRAME_LOLLIPOP)
                  .setColor(resources.getColor(R.color.colorAccent))
                  .setAnimations(Style.ANIMATIONS_POP).show()
        loginEmail.requestFocus()
        return@OnClickListener
      }

      if (password.isEmpty()) {
          SuperActivityToast.create(
              requireContext(),
              Style(),
              Style.TYPE_STANDARD
          )
                  .setText(getString(R.string.invalidpassword))
                  .setDuration(Style.DURATION_SHORT)
                  .setFrame(Style.FRAME_LOLLIPOP)
                  .setColor(resources.getColor(R.color.colorAccent))
                  .setAnimations(Style.ANIMATIONS_POP).show()
        loginPass.requestFocus()
        return@OnClickListener
      }

      if (password.length<6) {
          SuperActivityToast.create(
              requireContext(),
              Style(),
              Style.TYPE_STANDARD
          )
                  .setText(getString(R.string.invalidpassword))
                  .setDuration(Style.DURATION_SHORT)
                  .setFrame(Style.FRAME_LOLLIPOP)
                  .setColor(resources.getColor(R.color.colorAccent))
                  .setAnimations(Style.ANIMATIONS_POP).show()
        loginPass.requestFocus()
        return@OnClickListener
      }

      val im = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
      im?.hideSoftInputFromWindow(loginEmail.windowToken,
          InputMethodManager.RESULT_UNCHANGED_SHOWN
      )
      //loginUser(loginEmail.text.toString(),loginPass.text.toString())


        var progressBar = ProgressDialog(requireContext())
        progressBar.setCancelable(false)
        progressBar.setMessage(getString(R.string.loadingmessage))
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressBar.show()
        Handler().postDelayed({
            //The following code will execute after the 5 seconds.

            try {
                progressBar.dismiss()
                val i = Intent(requireActivity(), MainActivity::class.java)
                requireActivity().startActivity(i)
                requireActivity().finish()

            } catch (ignored: Exception) {
                ignored.printStackTrace()
            }
        }, 1000)  // Give a 5 seconds delay.



    })


    sifremiunuttum.setOnClickListener {


    }


  }

    private fun loginUser(email : String, password : String) {

        val progressBar = ProgressDialog(requireContext())
        progressBar.setCancelable(false)
        progressBar.setMessage(getString(R.string.loadingmessage))
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressBar.show()

        val model = LoginModel()
        model.email = email
        model.password = password

        ApiClient.getClient(requireContext())
                .create(ResponsibleAPI::class.java)
                .login(model)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response ->
                            progressBar.dismiss()
                            if (response != null) {


                            } else
                                throw Throwable(getString(R.string.responserror))


                        },
                        {
                            progressBar.dismiss()
                            SuperActivityToast.create(
                                requireContext(),
                                Style(),
                                Style.TYPE_STANDARD
                            )
                                    .setText(it.message)
                                    .setDuration(Style.DURATION_SHORT)
                                    .setFrame(Style.FRAME_LOLLIPOP)
                                    .setColor(resources.getColor(R.color.colorAccent))
                                    .setAnimations(Style.ANIMATIONS_POP).show()
                            loginEmail.requestFocus()

                        }
                )

    }

}