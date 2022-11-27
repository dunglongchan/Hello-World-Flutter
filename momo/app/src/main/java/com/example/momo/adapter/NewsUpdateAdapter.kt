package com.example.momo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.momo.databinding.ItemNewUpdateBinding
import com.example.momo.model.NewUpdateModel

class NewsUpdateAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listItem: ArrayList<NewUpdateModel> = ArrayList()

    fun setData(list: ArrayList<NewUpdateModel>) {
        this.listItem.clear()
        this.listItem.addAll(list)
    }

    class ViewHolder(binding: ItemNewUpdateBinding) : RecyclerView.ViewHolder(binding.root) {
        val img = binding.ivImg
        val title = binding.tvTitle
        val content = binding.tvContent
        val btnTxt = binding.tvClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemNewUpdateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        val item = listItem[position]

        Glide.with(context).load(item.img).into(viewHolder.img)
        viewHolder.title.text = item.title
        viewHolder.content.text = item.content
        viewHolder.btnTxt.text = item.btnText
    }

    override fun getItemCount(): Int {
        return listItem.size
    }
}