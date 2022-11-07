package com.example.momo.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.momo.Common
import com.example.momo.adapter.IntroAdapter
import com.example.momo.databinding.ActivityIntroBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class IntroActivity : AppCompatActivity() {

     lateinit var binding: ActivityIntroBinding
    private var adapter: IntroAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    @SuppressLint("CheckResult")
    private fun setupView() {
        adapter = IntroAdapter(this)
        binding.viewpager.adapter = adapter

        TabLayoutMediator(
            binding.tabLayout, binding.viewpager
        ) { _: TabLayout.Tab?, _: Int -> }.attach()

        when (binding.viewpager.currentItem) {
            0 -> {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(binding.viewpager.currentItem))
            }
            1 -> {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(binding.viewpager.currentItem + 1))
            }
            2 -> {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(binding.viewpager.currentItem + 1))
            }
            else -> {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(binding.viewpager.currentItem + 1))
            }
        }

        binding.tvNext.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            startToNewActivity(Common.isNetworkWorking(this@IntroActivity))
        }
    }

    fun startToNewActivity(isNetworkConnected: Boolean) {
        if (!isNetworkConnected) {
            Common.openRequireNetworkDialog(this@IntroActivity)
        }
        startActivity(Intent(this@IntroActivity, LoginActivity::class.java))
        finish()
    }
}