package com.example.momo.fragment

import com.example.momo.databinding.FragmentVoucherBinding
import com.example.momo.fragment.BaseFragment

class VoucherFragment : BaseFragment<FragmentVoucherBinding>() {
    override fun getViewBinding(): FragmentVoucherBinding {
        return FragmentVoucherBinding.inflate(layoutInflater)
    }

    override fun setup() {
    }

}
