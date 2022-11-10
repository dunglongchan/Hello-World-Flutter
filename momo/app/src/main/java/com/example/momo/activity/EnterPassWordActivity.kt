package com.example.momo.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.momo.databinding.ActivityEnterPasswordBinding

class EnterPassWordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnterPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityEnterPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}