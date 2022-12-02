package com.example.momo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.momo.databinding.ItemContactBinding
import com.example.momo.model.UserModel
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class ContactAdapter(val context: Context, val listener: OnContactClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listContact: ArrayList<UserModel> = ArrayList()

    fun setListContact(list: ArrayList<UserModel>) {
        listContact.clear()
        listContact.addAll(list)
        notifyDataSetChanged()
    }

    interface OnContactClickListener {
        fun onContactClickListener(pos: Int)
    }

    class ContactViewHolder(binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        val avatar = binding.rivAvatar
        val name = binding.tvName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (listContact.isNotEmpty()) {
            val contact = listContact[position]
            val viewHolder = holder as ContactViewHolder

            Glide.with(context).load(contact.avatar).into(viewHolder.avatar)
            viewHolder.name.text = contact.name

            viewHolder.itemView.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
                listener.onContactClickListener(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return listContact.size
    }
}