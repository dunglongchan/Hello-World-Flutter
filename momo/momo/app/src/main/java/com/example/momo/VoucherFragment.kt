package com.example.momo

import androidx.fragment.app.Fragment
import com.example.momo.databinding.FragmentVoucherBinding

class VoucherFragment : BaseFragment<FragmentVoucherBinding>() {
    override fun getViewBinding(): FragmentVoucherBinding {
        return FragmentVoucherBinding.inflate(layoutInflater)
    }

    override fun setup() {
    }

}
