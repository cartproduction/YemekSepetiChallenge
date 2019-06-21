package com.solvepark.yemekgelircustomer

import android.os.Bundle
import android.view.*
import android.view.Menu
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.johnpersano.supertoasts.library.Style
import com.github.johnpersano.supertoasts.library.SuperActivityToast
import com.raventech.fujibas.interfaces.ApiClient
import com.raventech.fujibas.interfaces.ResponsibleAPI
import com.yemeksepeti.challenge.R
import com.yemeksepeti.challenge.databinding.UserprofileBinding
import com.yemeksepeti.challenge.models.UserDetailsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.userprofile.*
import java.util.*

class UserProfile: Fragment() {
    lateinit var user : UserDetailsModel
    private lateinit var binding: UserprofileBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        requireActivity().apptitle_text.text = "Profil"
        requireActivity().apptitle_text.visibility = View.VISIBLE
        requireActivity().imageView18.visibility = View.GONE
        var drawable = ResourcesCompat.getDrawable(resources, R.mipmap.back, null)
        drawable = DrawableCompat.wrap(drawable!!)
        requireActivity().toolbar.navigationIcon = drawable

        binding = UserprofileBinding.inflate(LayoutInflater.from(context))!!

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bundle = this.arguments
        if (bundle != null)
            user = bundle.getSerializable("dummyUser") as UserDetailsModel
        binding.data = user
        loadImage(binding.profileImage,user.profilImage!!)

    }

    @BindingAdapter("bind:imageUrl")
    fun loadImage(view: ImageView, imageUrl: Int) {
        Glide.with(view.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_collections_24dp)
            .into(view)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menutext, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.optionitem -> {
                saveUserInfo()
            }

            android.R.id.home -> {
                requireActivity().onBackPressed()
            }


            else ->
                super.onOptionsItemSelected(item)
        }

        return true

    }

    private fun saveUserInfo(): Boolean {


        if (editname.text.isEmpty()||editlastname.text.isEmpty()||editdate.text.isEmpty() || editphone.text.isEmpty()  || editadress.text.isEmpty() ||editemail.text.isEmpty()) {
            SuperActivityToast.create(requireContext(), Style(), Style.TYPE_STANDARD)
                    .setText(getString(R.string.pleasefillform))
                    .setDuration(Style.DURATION_SHORT)
                    .setFrame(Style.FRAME_LOLLIPOP)
                    .setColor(resources.getColor(R.color.colorAccent))
                    .setAnimations(Style.ANIMATIONS_POP).show()


        }else {
            //saveUser(user)
            requireActivity().onBackPressed()
        }

        return true
    }

    private fun saveUser(model: UserDetailsModel) {


        ApiClient.getClient(requireContext())
            .create(ResponsibleAPI::class.java)
            .saveUserInfo(model)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (response != null) {


                    } else
                        throw Throwable(getString(R.string.responserror))


                },
                {
                    SuperActivityToast.create(requireContext(), Style(), Style.TYPE_STANDARD)
                        .setText(it.message)
                        .setDuration(Style.DURATION_SHORT)
                        .setFrame(Style.FRAME_LOLLIPOP)
                        .setColor(resources.getColor(R.color.colorAccent))
                        .setAnimations(Style.ANIMATIONS_POP).show()

                }
            )
    }




}