package com.example.momo.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.momo.common.Constant
import com.example.momo.databinding.ActivityEnterPasswordBinding
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class EnterPassWordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnterPasswordBinding
    private var passwordType: Int = 4
    private var passWord = ""
    private var errorPassword = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        passwordType = (Constant.userModel.security[Constant.TYPE] as Long).toInt()
        passWord = Constant.userModel.security[Constant.PASSWORD] as String

        setUpView()
    }

    @SuppressLint("CheckResult")
    private fun setUpView() {
        binding.btnLogin.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            if (errorPassword > 4) {
                Toast.makeText(this, "Khong Duoc Nhap Pass Nua", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                if (checkValidatePassword()) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    errorPassword++
                    return@subscribe
                }
            }
        }
    }

    private fun checkValidatePassword(): Boolean {
        val pass = binding.textInput.text!!.trim().toString()
        if (pass.length != passwordType) {
            return false
        }
        if (pass != passWord) {
            return false
        }
        return true
    }
}