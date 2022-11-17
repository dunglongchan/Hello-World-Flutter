package com.example.momo.fragment

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.momo.R
import com.example.momo.adapter.MomoSuggestAdapter
import com.example.momo.adapter.NewPromoAdapter
import com.example.momo.adapter.NewsUpdateAdapter
import com.example.momo.adapter.YourPromoAdapter
import com.example.momo.databinding.FragmentHomeBinding
import com.example.momo.model.MomoSuggestModel
import com.example.momo.model.NewPromoModel
import com.example.momo.model.NewUpdateModel
import com.example.momo.model.YourPromoModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun setup() {
        setUpInforMoMoSuggest()
        setUpInForNewsUpdate()
        setUpInForYourPromo()
        setUpInForNewPromo()

    }

    private fun setUpInForYourPromo() {

        val list: ArrayList<YourPromoModel> = ArrayList()

        list.add(YourPromoModel(R.drawable.ic_dmeo, "Uu dai vang", "Giam 10%", "HSD: 22/10/2022"))
        list.add(YourPromoModel(R.drawable.ic_dmeo, "Uu dai vang", "Giam 10%", "HSD: 22/10/2022"))
        list.add(YourPromoModel(R.drawable.ic_dmeo, "Uu dai vang", "Giam 10%", "HSD: 22/10/2022"))
        list.add(YourPromoModel(R.drawable.ic_dmeo, "Uu dai vang", "Giam 10%", "HSD: 22/10/2022"))
        list.add(YourPromoModel(R.drawable.ic_dmeo, "Uu dai vang", "Giam 10%", "HSD: 22/10/2022"))

        val adapter = YourPromoAdapter(requireContext())
        adapter.setData(list)
        binding.rcvYourPromo.adapter = adapter
        binding.rcvYourPromo.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpInForNewPromo() {

        val list: ArrayList<NewPromoModel> = ArrayList()

        list.add(NewPromoModel(R.drawable.home_banner, "Uu dai vang", "san ngay"))
        list.add(NewPromoModel(R.drawable.home_banner, "Uu dai vang", "san ngay"))
        list.add(NewPromoModel(R.drawable.home_banner, "Uu dai vang", "san ngay"))
        list.add(NewPromoModel(R.drawable.home_banner, "Uu dai vang", "san ngay"))
        list.add(NewPromoModel(R.drawable.home_banner, "Uu dai vang", "san ngay"))
        list.add(NewPromoModel(R.drawable.home_banner, "Uu dai vang", "san ngay"))

        val adapter = NewPromoAdapter(requireContext())
        adapter.setData(list)
        binding.rcvNewPromo.adapter = adapter
        binding.rcvNewPromo.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun setUpInForNewsUpdate() {

        val list: ArrayList<NewUpdateModel> = ArrayList()

        list.add(
            NewUpdateModel(
                R.drawable.home_banner,
                "u dai vang",
                "Tận hưởng lãi suất tiết kiệm lớn lên đến 20% MỖI NĂM",
                "Tan huong ngay"
            )
        )
        list.add(
            NewUpdateModel(
                R.drawable.home_banner,
                "u dai vang",
                "Tận hưởng lãi suất tiết kiệm lớn lên đến 20% MỖI NĂM",
                "Tan huong ngay"
            )
        )
        list.add(
            NewUpdateModel(
                R.drawable.home_banner,
                "u dai vang",
                "Tận hưởng lãi suất tiết kiệm lớn lên đến 20% MỖI NĂM",
                "Tan huong ngay"
            )
        )
        list.add(
            NewUpdateModel(
                R.drawable.home_banner,
                "u dai vang",
                "Tận hưởng lãi suất tiết kiệm lớn lên đến 20% MỖI NĂM",
                "Tan huong ngay"
            )
        )
        list.add(
            NewUpdateModel(
                R.drawable.home_banner,
                "u dai vang",
                "Tận hưởng lãi suất tiết kiệm lớn lên đến 20% MỖI NĂM",
                "Tan huong ngay"
            )
        )

        val adapter = NewsUpdateAdapter(requireContext())
        adapter.setData(list)
        binding.rcvNewUpdate.adapter = adapter
        binding.rcvNewUpdate.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpInforMoMoSuggest() {

        val list: ArrayList<MomoSuggestModel> = ArrayList()

        list.add(MomoSuggestModel(R.drawable.ic_menu_listrik, "Mua ve xem phim"))
        list.add(MomoSuggestModel(R.drawable.ic_menu_listrik, "Mua ve xem phim"))
        list.add(MomoSuggestModel(R.drawable.ic_menu_listrik, "Mua ve xem phim"))
        list.add(MomoSuggestModel(R.drawable.ic_menu_listrik, "Mua ve xem phim"))
        list.add(MomoSuggestModel(R.drawable.ic_menu_listrik, "Mua ve xem phim"))
        list.add(MomoSuggestModel(R.drawable.ic_menu_listrik, "Mua ve xem phim"))
        list.add(MomoSuggestModel(R.drawable.ic_menu_listrik, "Mua ve xem phim"))
        list.add(MomoSuggestModel(R.drawable.ic_menu_listrik, "Mua ve xem phim"))

        val adapter = MomoSuggestAdapter(requireContext())
        adapter.setData(list)
        binding.rcvMomoSuggest.adapter = adapter
        binding.rcvMomoSuggest.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

    }

}
