package com.yemeksepeti.challenge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yemeksepeti.challenge.R
import com.yemeksepeti.challenge.databinding.ItemuserBinding
import com.yemeksepeti.challenge.models.UserModel


class UserRecyclerViewAdapter(val userList: ArrayList<UserModel>, internal var context: Context, internal var fragmentManager: FragmentManager) : RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //inflate the layout file
        val binding = ItemuserBinding.inflate(LayoutInflater.from(parent.context))
        this.binding = binding!!

        return ViewHolder(binding)
    }

    private lateinit var binding: ItemuserBinding

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    inner class ViewHolder(private val binding: ItemuserBinding) : RecyclerView.ViewHolder(binding.root){
        lateinit var item: UserModel
        fun bind(item: UserModel) {
            this.item = item
            binding.data = item
            loadImage(binding.imageView17, item.profilImage!!)

            binding.executePendingBindings()
        }

    }

    @BindingAdapter("bind:imageUrl")
    fun loadImage(view: ImageView, imageUrl: Int) {
        Glide.with(view.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_collections_24dp)
                .into(view)
    }


}