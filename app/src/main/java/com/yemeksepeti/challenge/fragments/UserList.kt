package com.yemeksepeti.challenge.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import android.view.LayoutInflater
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.solvepark.yemekgelircustomer.UserProfile
import com.yemeksepeti.challenge.R
import com.yemeksepeti.challenge.activities.UserProfileActivity
import com.yemeksepeti.challenge.adapters.UserRecyclerViewAdapter
import com.yemeksepeti.challenge.application.YemekApp.Companion.addFragment
import com.yemeksepeti.challenge.helpers.ItemClickSupport
import com.yemeksepeti.challenge.helpers.MarginItemDecoration
import com.yemeksepeti.challenge.models.UserModel
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.userlist.*
import java.util.*


class UserList: Fragment() {

    private var proArray: ArrayList<UserModel> = ArrayList()
    private var productAdapter: UserRecyclerViewAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        requireActivity().apptitle_text.text = getString(R.string.userslist)
        requireActivity().apptitle_text.visibility = View.VISIBLE
        requireActivity().imageView18.visibility = View.GONE
        var drawable = ResourcesCompat.getDrawable(resources, R.mipmap.back, null)
        drawable = DrawableCompat.wrap(drawable!!)
        requireActivity().toolbar.navigationIcon = drawable
        return inflater.inflate(R.layout.userlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initProductRecycleView()

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

    private fun initProductRecycleView() {

        val user1 = UserModel("user134325432",R.drawable.girl, "Ayşe", "Fatma","aysefatma@mail.com")
        val user2 = UserModel("user123554351",R.drawable.man, "Hasan", "Aslan","hasanaslan@mail.com")
        val user3 = UserModel("user113425433",R.drawable.boy, "Kemal", "Taşkıran", "kemaltaskiran@mail.com")
        val user4 = UserModel("user106780255",R.drawable.girl, "Pelin", "Ertürk", "pelinerturk@mail.com")

        proArray.clear()
        proArray.add(user1)
        proArray.add(user2)
        proArray.add(user3)
        proArray.add(user4)

        userList!!.addItemDecoration(
            MarginItemDecoration(
            resources.getDimension(R.dimen.material_drawer_padding_half).toInt(),0)
        )
        productAdapter = UserRecyclerViewAdapter(proArray, requireContext(),requireFragmentManager())
        val gridLayoutManager = GridLayoutManager(requireContext(), 1)
        userList!!.layoutManager = gridLayoutManager
        userList!!.adapter = productAdapter

        ItemClickSupport.addTo(userList).setOnItemClickListener { menulist, position, v ->
            var progressBar = ProgressDialog(requireContext())
            progressBar.setCancelable(false)
            progressBar.setMessage(getString(R.string.loadingmessage))
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressBar.show()
            Handler().postDelayed({
                //The following code will execute after the 5 seconds.

                try {
                    progressBar.dismiss()
                    val i = Intent(requireActivity(), UserProfileActivity::class.java)
                    val bundle =  Bundle()
                    bundle.putSerializable("user", proArray[position])
                    i.putExtras(bundle)
                    requireActivity().startActivity(i)

                } catch (ignored: Exception) {
                    ignored.printStackTrace()
                }
            }, 1000)  // Give a 5 seconds delay.

        }


    }


}