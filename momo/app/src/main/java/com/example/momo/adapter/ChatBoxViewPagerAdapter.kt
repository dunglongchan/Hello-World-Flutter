package com.example.momo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.momo.fragment.ChatFragment
import com.example.momo.fragment.NewfeedFragment

class ChatBoxViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ChatFragment()
            }
            else -> {
                NewfeedFragment()
            }
        }
    }
}