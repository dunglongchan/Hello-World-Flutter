package com.example.momo.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.momo.fragment.QrCodeFragment
import com.example.momo.R
import com.example.momo.ViewPagerAdapter
import com.example.momo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(this)
        binding.viewpager.adapter = adapter
        binding.viewpager.offscreenPageLimit = 4
        binding.viewpager.isUserInputEnabled = false

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        binding.nvBtm.menu.findItem(R.id.home).isChecked = true
                    }
                    1 -> {
                        binding.nvBtm.menu.findItem(R.id.voucher).isChecked = true
                    }
                    2 -> {
                        binding.nvBtm.menu.findItem(R.id.qrcode).isChecked = true
                    }
                    3 -> {
                        binding.nvBtm.menu.findItem(R.id.chat).isChecked = true
                    }
                    4 -> {
                        binding.nvBtm.menu.findItem(R.id.profile).isChecked = true
                    }
                }
            }
        })

        binding.nvBtm.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    binding.viewpager.currentItem = 0
                    binding.nvBtm.visibility = View.VISIBLE
                    true
                }
                R.id.voucher -> {
                    binding.nvBtm.visibility = View.VISIBLE
                    binding.viewpager.currentItem = 1
                    true
                }
                R.id.qrcode -> {
                    binding.viewpager.currentItem = 2
                    binding.nvBtm.visibility = View.GONE
                    true
                }
                R.id.chat -> {
                    binding.viewpager.currentItem = 3
                    binding.nvBtm.visibility = View.VISIBLE
                    true
                }
                R.id.profile -> {
                    binding.viewpager.currentItem = 4
                    binding.nvBtm.visibility = View.VISIBLE
                    true
                }
                else -> false

            }
        }

        QrCodeFragment.close.observe(this, Observer {
            if (it) {
                binding.viewpager.currentItem = 0
                binding.nvBtm.visibility = View.VISIBLE
                QrCodeFragment.close.value = false
            }
        })
    }
}