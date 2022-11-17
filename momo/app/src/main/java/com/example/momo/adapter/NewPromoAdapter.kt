package com.example.momo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.momo.databinding.ItemNewPromoBinding
import com.example.momo.model.NewPromoModel

class NewPromoAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listItem: ArrayList<NewPromoModel> = ArrayList()

    fun setData(list: ArrayList<NewPromoModel>) {
        this.listItem.clear()
        this.listItem.addAll(list)
    }

    class ViewHolder(binding: ItemNewPromoBinding) : RecyclerView.ViewHolder(binding.root) {
        val img = binding.ivImg
        val title = binding.tvTitle
        val txtClick = binding.tvClivk
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemNewPromoBinding.inflate(
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
        viewHolder.txtClick.text = item.textClick
    }

    override fun getItemCount(): Int {
        return listItem.size
    }
}