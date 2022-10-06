package com.example.momo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.momo.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.nvBtm.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.home -> {

                }
                R.id.voucher -> {

                }
                R.id.qrcode -> {

                }
                R.id.chat -> {

                }
                R.id.profile -> {

                }

            }
        }
    }
}