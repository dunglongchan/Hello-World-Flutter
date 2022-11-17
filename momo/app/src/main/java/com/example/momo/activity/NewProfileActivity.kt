package com.example.momo.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.momo.common.Constant
import com.example.momo.databinding.ActivityNewProfileBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class NewProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    @SuppressLint("CheckResult")
    private fun setupView() {

        binding.btnContinue.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            Constant.userModel.information[Constant.GENDER] = 1
            Constant.userModel.name = binding.textInput1.text.toString()
            Constant.userModel.information[Constant.EMAIL] = binding.textInput2.text.toString()

            val data = hashMapOf(
                Constant.USER_ID to Constant.userModel.user_id,
                Constant.NAME to Constant.userModel.name,
                Constant.INFORMATION to hashMapOf(
                    Constant.EMAIL to Constant.userModel.information[Constant.EMAIL],
                    Constant.NAME to Constant.userModel.information[Constant.GENDER],
                    Constant.NATIONAL to Constant.userModel.information[Constant.NATIONAL],
                ),
                Constant.SECURITY to hashMapOf(
                    Constant.PASSWORD to Constant.userModel.security[Constant.PASSWORD],
                    Constant.TYPE to Constant.userModel.security[Constant.TYPE]
                )
            )

            FirebaseFirestore.getInstance().collection("user_data")
                .document(Constant.userModel.user_id).set(data)

            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}