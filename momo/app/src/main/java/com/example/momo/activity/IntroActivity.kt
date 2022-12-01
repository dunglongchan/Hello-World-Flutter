package com.example.momo.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.momo.R
import com.example.momo.adapter.IntroAdapter
import com.example.momo.common.Common
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

    @SuppressLint("CheckResult", "ResourceAsColor")
    private fun setupView() {
        adapter = IntroAdapter(this)
        binding.viewpager.adapter = adapter

        binding.tvVietnamese.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            binding.tvVietnamese.setBackgroundResource(R.drawable.custom_backgr_7)
            binding.tvVietnamese.setTextColor(Color.parseColor("#ffffff"))

            binding.tvEnglish.setBackgroundResource(R.drawable.custom_backgr_8)
            binding.tvEnglish.setTextColor(Color.parseColor("#000000"))

            Common.setLocale(this, "vi")

            finish();
            startActivity(intent);
        }

        binding.tvEnglish.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            binding.tvEnglish.setBackgroundResource(R.drawable.custom_backgr_7)
            binding.tvEnglish.setTextColor(Color.parseColor("#ffffff"))

            binding.tvVietnamese.setBackgroundResource(R.drawable.custom_backgr_8)
            binding.tvVietnamese.setTextColor(Color.parseColor("#000000"))

            Common.setLocale(this, "en")

            finish();
            startActivity(intent);
        }

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