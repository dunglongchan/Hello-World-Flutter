package com.example.momo.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.momo.R
import com.example.momo.adapter.ContactAdapter
import com.example.momo.common.Constant
import com.example.momo.database.AppDataBase
import com.example.momo.databinding.ActivityTransferMoneyBinding
import com.example.momo.model.ContactInfor
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class TransferMoneyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransferMoneyBinding
    private lateinit var contactAdapter: ContactAdapter
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
        contactAdapter = ContactAdapter(this@TransferMoneyActivity,
            object : ContactAdapter.OnContactClickListener {
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
//        contactAdapter.setListContact(list)
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