package com.example.momo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.momo.R
import com.example.momo.common.Constant
import com.example.momo.databinding.ItemBankBinding
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class BankAcountAdapter(
    val context: Context,
    val viewType: Int,
    val listener: BankAccountListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var bankList = Constant.userModel.bankAccount

    class BankInforViewHolder(binding: ItemBankBinding) : RecyclerView.ViewHolder(binding.root) {
        val logo = binding.ivBankLogo
        val bankName = binding.tvBankName
        val feePrice = binding.tvFeePrice
        val layout = binding.layout
        val tick = binding.ivTick
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BankInforViewHolder(
            ItemBankBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    interface BankAccountListener {
        fun onClickAddBankAccount(pos: Int)
        fun onClickChooseBankAccount(pos: Int)
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (bankList.isEmpty()) {
            val viewHolder = holder as BankInforViewHolder
            viewHolder.bankName.text = "Add Bank"
            Glide.with(context).load(R.drawable.logo_momo).into(viewHolder.logo)
            viewHolder.feePrice.visibility = View.GONE

            viewHolder.itemView.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
                viewHolder.layout.setBackgroundResource(R.drawable.custom_backgr_4)
                listener.onClickAddBankAccount(position)
            }
        } else {
            val viewHolder = holder as BankInforViewHolder
            val bank = bankList[position]
            viewHolder.bankName.text = bank
            Glide.with(context).load(R.drawable.logo_momo).into(viewHolder.logo)
            viewHolder.feePrice.visibility = View.VISIBLE
            viewHolder.feePrice.text = "Free"

            viewHolder.itemView.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
                viewHolder.layout.setBackgroundResource(R.drawable.custom_backgr_4)
                listener.onClickChooseBankAccount(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return if (bankList.isEmpty()) 1 else bankList.size
    }
}