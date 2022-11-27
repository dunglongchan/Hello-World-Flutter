package com.example.momo.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.momo.adapter.BankAcountAdapter
import com.example.momo.common.Constant
import com.example.momo.databinding.ActivityTopUpBalanceBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class TopUpBalanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTopUpBalanceBinding
    private var bankAccount = ""
    private var balance = Constant.userModel.security[Constant.BALANCE] as Long

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTopUpBalanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
        initData()
    }

    private fun initData() {
        binding.tvBalance.text = Constant.userModel.security[Constant.BALANCE].toString() + "vnd"
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    private fun setUpView() {
        binding.ivBack.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            onBackPressed()
        }
        binding.ivInformation.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {

        }
        binding.llTopUpAtStation.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {

        }
        binding.llAddBankAccount.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {

        }
        binding.tvTopUp.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            val topup: String = binding.textInputTopup.text.toString()
            balance += topup.toLong()
            Constant.userModel.security[Constant.BALANCE] = balance

            val data = Constant.getUserModelData()

            FirebaseFirestore.getInstance().collection("user_data")
                .document(Constant.userModel.user_id).update(data).addOnCompleteListener {
                    Toast.makeText(
                        this@TopUpBalanceActivity,
                        "Success Topup $topup",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
        }

        val bankAcountAdapter = BankAcountAdapter(
            this@TopUpBalanceActivity,
            0,
            object : BankAcountAdapter.BankAccountListener {
                override fun onClickAddBankAccount(pos: Int) {
                    startActivity(
                        Intent(
                            this@TopUpBalanceActivity,
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