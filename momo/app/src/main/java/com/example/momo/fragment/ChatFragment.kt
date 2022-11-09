package com.example.momo.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.momo.R
import com.example.momo.adapter.ChatAdapter
import com.example.momo.databinding.FragmentChatBinding

class ChatFragment : BaseFragment<FragmentChatBinding>() {
    override fun getViewBinding(): FragmentChatBinding {
        return FragmentChatBinding.inflate(layoutInflater)
    }

    override fun setup() {
        val chatList: ArrayList<Int> = ArrayList()
        chatList.add(R.drawable.avatar)
        chatList.add(R.drawable.avatar)
        chatList.add(R.drawable.avatar)
        chatList.add(R.drawable.avatar)
        chatList.add(R.drawable.avatar)
        chatList.add(R.drawable.avatar)
        chatList.add(R.drawable.avatar)
        chatList.add(R.drawable.avatar)
        chatList.add(R.drawable.avatar)
        chatList.add(R.drawable.avatar)

        val adapter = ChatAdapter(chatList, requireContext())
        binding.rcvChat.adapter = adapter
        binding.rcvChat.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }
}