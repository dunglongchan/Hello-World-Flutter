package com.example.momo.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.momo.R
import com.example.momo.common.Constant
import com.example.momo.database.AppDataBase
import com.example.momo.database.Transaction
import com.example.momo.databinding.ActivityTransferDetailBinding
import com.example.momo.model.ContactInfor
import com.example.momo.model.ConversationModel
import com.example.momo.model.TransactionModel
import com.example.momo.model.UserModel
import com.google.firebase.firestore.FirebaseFirestore
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class TransferDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransferDetailBinding
    private lateinit var appDataBase: AppDataBase
    private lateinit var contactInfor: ContactInfor
    private lateinit var receiverPhoneNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTransferDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDataBase = AppDataBase.getAppDatabase(this@TransferDetailActivity)!!

        val intent = intent
        val stringExtra = intent.getStringExtra(Constant.PHONE_NUMBER).toString()
        if (stringExtra.isNotEmpty()) {
            val contact = appDataBase.ContactDao()
                .getContact(stringExtra)
            contactInfor = ContactInfor(R.drawable.logo_momo, contact.name, contact.phoneNumber)
            receiverPhoneNumber =
                if (contact.phoneNumber.length == 10) contact.phoneNumber.substring(0) else contactInfor.phoneNumber
        } else finish()

        setupview()
        initData()
    }

    private fun initData() {

    }

    @SuppressLint("CheckResult", "SetTextI18n")
    private fun setupview() {
        binding.textInputMessage.setText("${Constant.userModel.name} chuyen")

        binding.ivBack.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            onBackPressed()
        }
        binding.etAmong.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tvNext.isClickable = !s!!.isEmpty()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        binding.tvNext.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            val tmp = binding.etAmong.text.toString().toLong()
            if (tmp > (Constant.userModel.security[Constant.BALANCE] as Long)) {
                Toast.makeText(this@TransferDetailActivity, "So du khong du", Toast.LENGTH_SHORT)
                    .show()
                return@subscribe
            }

            GlobalScope.launch {
                makeTransaction()
            }

        }
        binding.tvChange.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            finish()
        }

    }

    private suspend fun makeTransaction() {
        withContext(Dispatchers.Main) {

            val fireStore = FirebaseFirestore.getInstance().collection("user_data")
            fireStore.whereEqualTo("user_id", "user_84$receiverPhoneNumber")
                .get()
                .addOnSuccessListener { r ->
                    if (!r.isEmpty) {
                        var userModel: UserModel = UserModel("", "")
                        for (doc in r) {
                            userModel = Constant.castDataToUserModel(doc.data)
                        }
                        var balance = userModel.security[Constant.BALANCE] as Long
                        userModel.security[Constant.BALANCE] =
                            balance + binding.etAmong.text.toString().toLong()
                        Constant.userModel.security[Constant.BALANCE] =
                            balance - binding.etAmong.text.toString().toLong()

                        val updateData1 = Constant.getUserModelData(userModel)
                        val updateData2 = Constant.getUserModelData(Constant.userModel)

                        fireStore.document(userModel.user_id).update(updateData1)
                        fireStore.document(Constant.userModel.user_id).update(updateData2)
                    }
                }
                .addOnFailureListener { Log.e("====", it.toString()) }

            val c = Calendar.getInstance().time
//            println("Current time => $c")

            val df = SimpleDateFormat("ddmmyyyy", Locale.getDefault())
            val formattedDate: String = df.format(c)

            val transaction = Transaction(
                "${Constant.userModel.user_id}_trans_user_84$receiverPhoneNumber",
                Constant.userModel.user_id,
                "user_84$receiverPhoneNumber",
                formattedDate,
                binding.etAmong.text.toString()
            )

            val transaction2 = TransactionModel(
                "${Constant.userModel.user_id}_trans_user_84$receiverPhoneNumber",
                Constant.userModel.user_id,
                "user_84$receiverPhoneNumber",
                formattedDate,
                binding.etAmong.text.toString()
            )

            val chat: ConversationModel = ConversationModel(
                "${Constant.userModel.user_id}_chat_user_84$receiverPhoneNumber",
                Constant.userModel.user_id,
                "user_84$receiverPhoneNumber",
                hashMapOf(
                    Constant.MESSAGE to binding.textInputMessage.text.toString(),
                    Constant.TYPE to 3,
                    Constant.VALUE to binding.etAmong.text.toString()
                )
            )

            appDataBase.TransactionDao().insertTransaction(transaction)

            FirebaseFirestore.getInstance().collection("transaction_data")
                .document(transaction.transactionId)
                .set(Constant.getTransactionModelData(transaction2))

            FirebaseFirestore.getInstance().collection("chat_data")
                .document()
                .set(Constant.getConversationModelData(chat))

            startActivity(Intent(this@TransferDetailActivity, MainActivity::class.java))
            TransferMoneyActivity.transactionMoneyActivity.finish()
            finish()
        }
    }
}