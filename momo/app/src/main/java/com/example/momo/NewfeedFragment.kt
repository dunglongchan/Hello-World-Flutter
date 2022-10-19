package com.example.momo

import androidx.fragment.app.Fragment
import com.example.momo.databinding.FragmentNewfeedBinding

class NewfeedFragment : BaseFragment<FragmentNewfeedBinding>() {
    override fun getViewBinding(): FragmentNewfeedBinding {
        return FragmentNewfeedBinding.inflate(layoutInflater)
    }

    override fun setup() {
    }
}