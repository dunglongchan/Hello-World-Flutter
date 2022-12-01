package com.example.momo.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.momo.databinding.ActivityWebViewBinding
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    @SuppressLint("CheckResult")
    private fun setupView() {
        binding.ivBack.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            onBackPressed()
        }

        binding.webView.loadUrl("https://momo.vn/tin-tuc/tin-tuc-su-kien/tham-gia-khoe-qua-momo-di-ngai-chi-tren-fanpage-3633")
    }
}