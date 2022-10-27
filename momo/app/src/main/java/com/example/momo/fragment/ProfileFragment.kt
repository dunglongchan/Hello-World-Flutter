package com.example.momo.fragment

import com.example.momo.databinding.FragmentProfileBinding
import com.example.momo.fragment.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun getViewBinding(): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun setup() {
    }

}
