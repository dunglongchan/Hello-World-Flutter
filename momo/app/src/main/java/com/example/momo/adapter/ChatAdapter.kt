package com.example.momo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.momo.R
import com.example.momo.databinding.ItemAvatarBinding
import com.example.momo.model.UserModel

class ChatAdapter(private val list: ArrayList<UserModel>, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(binding: ItemAvatarBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgView = binding.imgView
        val tvName = binding.tvName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemAvatarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        Glide.with(context).load(list[position].avatar).error(R.drawable.avatar).into(viewHolder.imgView)
        viewHolder.tvName.text = list[position].name
    }

    override fun getItemCount(): Int {
        return list.size
    }
}