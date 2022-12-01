package com.example.momo.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.momo.adapter.BankAcountAdapter
import com.example.momo.common.Constant
import com.example.momo.databinding.ActivityTransactionManagerBinding

class TransactionManagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionManagerBinding
    private var bankAccount = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTransactionManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        initData()
    }

    private fun initData() {

    }

    private fun setupView() {

        binding.ivBack.setOnClickListener { onBackPressed() }

        val bankAcountAdapter = BankAcountAdapter(
            this@TransactionManagerActivity,
            0,
            object : BankAcountAdapter.BankAccountListener {
                override fun onClickAddBankAccount(pos: Int) {
                    startActivity(
                        Intent(
                            this@TransactionManagerActivity,
                            TransactionManagerActivity::class.java
                        )
                    )
                }

                override fun onClickChooseBankAccount(pos: Int) {
                    bankAccount = Constant.userModel.bankAccount[pos]
                }

            })

        binding.rcvBankAccount.layoutManager = LinearLayoutManager(this)
        binding.rcvBankAccount.adapter = bankAcountAdapter

    }
}