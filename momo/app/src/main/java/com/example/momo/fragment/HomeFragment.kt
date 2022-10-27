package com.example.momo.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.momo.adapter.NewsAdapter
import com.example.momo.R
import com.example.momo.adapter.FoodAdapter
import com.example.momo.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun setup() {
        val listPhoto: ArrayList<Int> = ArrayList()
        listPhoto.add(R.drawable.img1)
        listPhoto.add(R.drawable.img1)
        listPhoto.add(R.drawable.img1)
        listPhoto.add(R.drawable.img1)

        val adapter = FoodAdapter(listPhoto, requireContext())
        binding.rcvFood.adapter = adapter
        binding.rcvFood.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val listNews: ArrayList<String> = ArrayList()
        listNews.add("Cerdas Finansial")
        listNews.add("Tài chính thông minh")
        listNews.add("Tài chính thông minh")

        val adapter2 = NewsAdapter(listNews, requireContext())
        binding.rcvMoreInfor.adapter = adapter2
        binding.rcvMoreInfor.layoutManager = LinearLayoutManager(requireContext())
    }

}
