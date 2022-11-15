package com.example.momo.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.momo.common.Constant
import com.example.momo.databinding.ActivityCreatePasswordBinding
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class CreatePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    @SuppressLint("CheckResult")
    private fun setupView() {
        binding.btnContinue.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            val str1 = binding.textInput1.text!!.trim().toString()
            val str2 = binding.textInput2.text!!.trim().toString()

            if (str1 != str2) {
                Toast.makeText(this, "Please try agian", Toast.LENGTH_SHORT).show()
                return@subscribe
            } else {
                Constant.userModel.security[Constant.PASSWORD] = str1
                Constant.userModel.security[Constant.TYPE] = 6
                startActivity(Intent(this, NewProfileActivity::class.java))
            }
        }
    }
}