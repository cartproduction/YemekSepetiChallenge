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
import androidx.recyclerview.widget.GridLayoutManager
import com.yemeksepeti.challenge.R
import com.yemeksepeti.challenge.activities.UserProfileActivity
import com.yemeksepeti.challenge.adapters.UserRecyclerViewAdapter
import com.yemeksepeti.challenge.helpers.ItemClickSupport
import com.yemeksepeti.challenge.helpers.MarginItemDecoration
import com.yemeksepeti.challenge.models.UserModel
import com.yemeksepeti.challenge.response.UserListResponse
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.userlist.*
import java.util.*
import kotlin.collections.ArrayList


class UserList: Fragment() {
    lateinit var userListResponse:UserListResponse
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

        val bundle = this.arguments
        if (bundle != null)
            userListResponse = bundle.getSerializable("userList") as UserListResponse

        proArray = userListResponse.users as ArrayList<UserModel>
        initUsersRecycleView()

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

    private fun initUsersRecycleView() {

        userList!!.addItemDecoration(
            MarginItemDecoration(
                resources.getDimension(R.dimen.material_drawer_padding_half).toInt(),0)
        )
        productAdapter = UserRecyclerViewAdapter(proArray, requireContext(),requireFragmentManager())
        val gridLayoutManager = GridLayoutManager(requireContext(), 1)
        userList!!.layoutManager = gridLayoutManager
        userList!!.adapter = productAdapter


        ItemClickSupport.addTo(userList).setOnItemClickListener { menulist, position, v ->
            val i = Intent(requireActivity(), UserProfileActivity::class.java)
            val bundle =  Bundle()
            bundle.putSerializable("user", proArray[position])
            i.putExtras(bundle)
            requireActivity().startActivity(i)

        }


    }


}