package com.example.momo.fragment

import com.example.momo.databinding.FragmentNewfeedBinding
import com.example.momo.fragment.BaseFragment

class NewfeedFragment : BaseFragment<FragmentNewfeedBinding>() {
    override fun getViewBinding(): FragmentNewfeedBinding {
        return FragmentNewfeedBinding.inflate(layoutInflater)
    }

    override fun setup() {
    }
}