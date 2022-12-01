package com.example.momo.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.momo.R
import com.example.momo.common.Constant
import com.example.momo.database.AppDataBase
import com.example.momo.databinding.ActivityTransferMoneyBinding
import com.example.momo.databinding.ItemContactBinding
import com.example.momo.model.ContactInfor
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class TransferMoneyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransferMoneyBinding
    private lateinit var contactAdapter: NearbyContactAdapter
    var list: ArrayList<ContactInfor> = ArrayList()
    private lateinit var appDataBase: AppDataBase

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var transactionMoneyActivity: Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transactionMoneyActivity = this@TransferMoneyActivity
        appDataBase = AppDataBase.getAppDatabase(this@TransferMoneyActivity)!!
        getNearbyContact()

        setupView()
        initData()
    }

    private fun initData() {
        contactAdapter = NearbyContactAdapter(this@TransferMoneyActivity,
            object : NearbyContactAdapter.OnContactClickListener {
                override fun onContactClickListener(pos: Int) {
                    startActivity(
                        Intent(
                            this@TransferMoneyActivity,
                            TransferDetailActivity::class.java
                        ).putExtra(Constant.PHONE_NUMBER, list[pos].phoneNumber)
                    )
                }
            })
        binding.rcvContact.layoutManager = GridLayoutManager(this@TransferMoneyActivity, 4)
        binding.rcvContact.adapter = contactAdapter
        contactAdapter.setListContact(list)
    }

    private fun getNearbyContact() {
        for (i in Constant.listContact) {
            if (i.isContactUseMomo) {
                list.add(ContactInfor(R.drawable.logo_momo, i.name, i.phoneNumber))
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun setupView() {
        binding.ivBack.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            onBackPressed()
        }
        binding.llSearch.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {

        }
        binding.ivScanQr.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {

        }
        binding.llTransferToMomo.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {

        }
        binding.llMoneyAlert.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {

        }
        binding.llMoneyLink.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {

        }
        binding.llTransferBank.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {

        }
        binding.llShareMoney.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {

        }
        binding.llDeposit.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {

        }
        binding.llSentToFund.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {

        }
        binding.llTransferLuckyMoney.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {

        }
    }
}

class NearbyContactAdapter(val context: Context, val listener: OnContactClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listContact: ArrayList<ContactInfor> = ArrayList()

    fun setListContact(list: ArrayList<ContactInfor>) {
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

            Glide.with(context).load(contact.avatar).error(R.drawable.avatar)
                .into(viewHolder.avatar)
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