package com.example.momo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.momo.R
import com.example.momo.common.Constant
import com.example.momo.database.Transaction
import com.example.momo.databinding.ItemTransactionBinding
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class TransactionHistoryAdapter(val context: Context, val listener: OnClickTransaction) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listTransaction: ArrayList<Transaction> = ArrayList()

    fun setData(listTransaction: ArrayList<Transaction>) {
        this.listTransaction.clear()
        this.listTransaction.addAll(listTransaction)
        notifyDataSetChanged()
    }

    interface OnClickTransaction {
        fun onClickTransaction(position: Int)
    }

    class TransactionViewHolder(binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val thumb = binding.ivThumb
        val tvName = binding.tvName
        val tvDate = binding.tvDate
        val tvValue = binding.tvValue
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TransactionViewHolder(
            ItemTransactionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n", "CheckResult")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val transaction = listTransaction[position]
        val viewHolder = holder as TransactionViewHolder

        when (transaction.receiver) {
            "buyData" -> {
                Glide.with(context).load(R.drawable.ic_menu_listrik).into(viewHolder.thumb)
                viewHolder.tvName.text = "Buy data"
                viewHolder.tvDate.text = transaction.date
                viewHolder.tvValue.text = "- ${transaction.value}d"
            }
            "buyTicket" -> {
                Glide.with(context).load(R.drawable.ic_menu_listrik).into(viewHolder.thumb)
                viewHolder.tvName.text = "Buy ticket"
                viewHolder.tvDate.text = (transaction.date)
                viewHolder.tvValue.text = "- 79000d"
            }
            Constant.userModel.user_id -> {
                Glide.with(context).load(R.drawable.ic_nhan_tien).into(viewHolder.thumb)
                viewHolder.tvName.text = "Receiver from: ${transaction.sender}"
                viewHolder.tvDate.text = transaction.date
                viewHolder.tvValue.text = "+ ${transaction.value}d"
            }
            else -> {
                Glide.with(context).load(R.drawable.ic_transfer).into(viewHolder.thumb)
                viewHolder.tvName.text = "Sent to: ${transaction.receiver}"
                viewHolder.tvDate.text = transaction.date
                viewHolder.tvValue.text = "- ${transaction.value}d"
            }
        }

        viewHolder.itemView.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            listener.onClickTransaction(position)
        }

    }

    override fun getItemCount(): Int {
        return listTransaction.size
    }
}