package com.example.momo.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.momo.R
import com.example.momo.common.Common
import com.example.momo.common.Constant
import com.example.momo.database.AppDataBase
import com.example.momo.database.Transaction
import com.example.momo.databinding.ActivityBuyDataBinding
import com.example.momo.model.TransactionModel
import com.google.firebase.firestore.FirebaseFirestore
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class BuyDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBuyDataBinding
    private lateinit var appDataBase: AppDataBase

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        val configuration = Configuration()
        configuration.setLocale(Locale(Common.getPreLanguage(this)!!))
        applyOverrideConfiguration(configuration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyDataBinding.inflate(layoutInflater)
        Common.setLocale(this,Common.getPreLanguage(this))
        setContentView(binding.root)
        appDataBase = AppDataBase.getAppDatabase(this)!!
        setUpView()
    }

    @SuppressLint("CheckResult")
    private fun setUpView() {

        binding.ivBack.setOnClickListener { onBackPressed() }

        binding.tvNext.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            val tmp = binding.etAmong.text.toString().toLong()
            if (tmp > (Constant.userModel.security[Constant.BALANCE] as Long)) {
                Toast.makeText(this@BuyDataActivity, "So du khong du", Toast.LENGTH_SHORT)
                    .show()
            }
            GlobalScope.launch {
                makeTransaction()
            }
            Toast.makeText(applicationContext, getString(R.string.buy_success), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private suspend fun makeTransaction() {
        withContext(Dispatchers.Main) {

            val fireStore = FirebaseFirestore.getInstance().collection("user_data")

            val balance = Constant.userModel.security[Constant.BALANCE] as Long

            Constant.userModel.security[Constant.BALANCE] = balance - 79000

            val updateData2 = Constant.getUserModelData(Constant.userModel)
            fireStore.document(Constant.userModel.user_id).update(updateData2)


            val c = Calendar.getInstance().time
//            println("Current time => $c")

            val df = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())
            val formattedDate: String = df.format(c)

            val transaction = Transaction(
                "${Constant.userModel.user_id}_trans_buyData_$formattedDate",
                Constant.userModel.user_id,
                "buyData",
                formattedDate,
                binding.etAmong.text.toString()

            )

            val transaction2 = TransactionModel(
                "${Constant.userModel.user_id}_trans_buyData_$formattedDate",
                Constant.userModel.user_id,
                "buyData",
                formattedDate,
                binding.etAmong.text.toString()
            )

            appDataBase.TransactionDao().insertTransaction(transaction)

            FirebaseFirestore.getInstance().collection("transaction_data")
                .document(transaction.transactionId)
                .set(Constant.getTransactionModelData(transaction2))
        }
    }

}