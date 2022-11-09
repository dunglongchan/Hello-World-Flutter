package com.example.momo.fragment

import androidx.viewpager2.widget.ViewPager2
import com.example.momo.adapter.ChatBoxViewPagerAdapter
import com.example.momo.databinding.FragmentChatboxBinding
import com.google.android.material.tabs.TabLayout

class ChatBoxFragment : BaseFragment<FragmentChatboxBinding>() {

    override fun getViewBinding(): FragmentChatboxBinding {
        return FragmentChatboxBinding.inflate(layoutInflater)
    }

    override fun setup() {

        val adapter = ChatBoxViewPagerAdapter(requireActivity())
        binding.viewpager2.adapter = adapter
        binding.viewpager2.offscreenPageLimit = 2
        binding.viewpager2.isUserInputEnabled = true


        binding.tablayout.addTab(binding.tablayout.newTab().setText("Chat"))
        binding.tablayout.addTab(binding.tablayout.newTab().setText("Newsfeed"))

        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewpager2.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        binding.viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tablayout.selectTab(binding.tablayout.getTabAt(position))
            }
        })
    }

}
