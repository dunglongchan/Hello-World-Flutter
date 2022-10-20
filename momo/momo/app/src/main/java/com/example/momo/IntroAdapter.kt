package com.example.momo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.momo.GuideFragment

class IntroAdapter(fa: FragmentActivity?) : FragmentStateAdapter(fa!!) {
    private val ARG_OBJECT = "position"

    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment = GuideFragment()
        val args = Bundle()
        // Our object is just an integer :-P
        args.putInt(ARG_OBJECT, position)
        fragment.arguments = args
        return fragment
    }

    override fun getItemCount(): Int {
        return 4
    }
}