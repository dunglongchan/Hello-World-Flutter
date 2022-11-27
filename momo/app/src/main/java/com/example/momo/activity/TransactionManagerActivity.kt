package com.example.momo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.momo.databinding.ActivityTransactionManagerBinding

class TransactionManagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionManagerBinding

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

    }
}