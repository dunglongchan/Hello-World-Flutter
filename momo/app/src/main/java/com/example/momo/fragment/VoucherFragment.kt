package com.example.momo.fragment

import com.example.momo.databinding.FragmentVoucherBinding

class VoucherFragment : BaseFragment<FragmentVoucherBinding>() {
    override fun getViewBinding(): FragmentVoucherBinding {
        return FragmentVoucherBinding.inflate(layoutInflater)
    }

    override fun setup() {
        binding.webView.loadUrl("https://momo.vn/tin-tuc/khuyen-mai")
    }

}
