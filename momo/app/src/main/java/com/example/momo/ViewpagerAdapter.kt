package com.example.momo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            1 -> return VoucherFragment()
            2 -> return QrCodeFragment()
            3 -> return ChatBoxFragment()
            4 -> return ProfileFragment()
            else -> return HomeFragment()
        }
    }
}