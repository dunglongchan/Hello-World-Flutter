package com.example.momo.fragment

import android.annotation.SuppressLint
import android.content.Intent
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.momo.R
import com.example.momo.activity.*
import com.example.momo.adapter.MomoSuggestAdapter
import com.example.momo.adapter.NewPromoAdapter
import com.example.momo.adapter.NewsUpdateAdapter
import com.example.momo.adapter.YourPromoAdapter
import com.example.momo.common.Constant
import com.example.momo.databinding.FragmentHomeBinding
import com.example.momo.model.MomoSuggestModel
import com.example.momo.model.NewPromoModel
import com.example.momo.model.NewUpdateModel
import com.example.momo.model.YourPromoModel
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit


class HomeFragment : BaseFragment<FragmentHomeBinding>(), OnItemVoucherCLick {
    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        binding.tvBalance.text = Constant.userModel.security[Constant.BALANCE].toString() + " vnd"
        Glide.with(requireContext()).load(Constant.userModel.avatar.toString())
            .error(R.drawable.avatar).into(binding.ivAvatar)
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun setup() {
        setUpInforMoMoSuggest()
        setUpInForNewsUpdate()
        setUpInForYourPromo()
        setUpInForNewPromo()


        binding.llSearch.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {

        }

        binding.tvAccount.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            startActivity(Intent(requireActivity(), TransactionManagerActivity::class.java))
        }

        binding.ivAvatar.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            startActivity(Intent(requireActivity(), PersonalInformationActivity::class.java))
        }

        binding.ivNotify.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            startActivity(Intent(requireContext(), PersonalInformationActivity::class.java))
        }

        binding.llQrReceiver.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            startActivity(Intent(requireContext(), PersonalInformationActivity::class.java))
        }

        binding.llQrSent.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {

        }

        binding.llScan.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            val manager: FragmentManager = requireActivity().supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.replace(com.example.momo.R.id.home, QrCodeFragment()).commit()
        }

        binding.llRecharge.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            startActivity(Intent(requireActivity(), TopUpBalanceActivity::class.java))
        }

        binding.llWithdraw.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            startActivity(Intent(requireActivity(), DepositeActivity::class.java))
        }

        binding.llTranferMoney.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            startActivity(Intent(requireActivity(), TransferMoneyActivity::class.java))
        }

        binding.llBuyCard.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            startActivity(Intent(requireActivity(), BuyDataActivity::class.java))
        }


    }

    private fun setUpInForYourPromo() {

        val list: ArrayList<YourPromoModel> = ArrayList()

        list.add(
            YourPromoModel(
                com.example.momo.R.drawable.ic_dmeo,
                "Uu dai vang",
                "Giam 10%",
                "HSD: 22/10/2022"
            )
        )
        list.add(
            YourPromoModel(
                com.example.momo.R.drawable.logo_momo,
                "Uu dai vang",
                "Giam 10%",
                "HSD: 22/10/2022"
            )
        )
        list.add(YourPromoModel(R.drawable.ic_dmeo, "Uu dai vang", "Giam 10%", "HSD: 22/10/2022"))
        list.add(YourPromoModel(R.drawable.logo_momo, "Uu dai vang", "Giam 10%", "HSD: 22/10/2022"))
        list.add(YourPromoModel(R.drawable.ic_dmeo, "Uu dai vang", "Giam 10%", "HSD: 22/10/2022"))

        val adapter = YourPromoAdapter(requireContext(), this)
        adapter.setData(list)
        binding.rcvYourPromo.adapter = adapter
        binding.rcvYourPromo.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpInForNewPromo() {

        val list: ArrayList<NewPromoModel> = ArrayList()

        list.add(NewPromoModel(R.drawable.momo_vc1, "Uu dai vang", "san ngay"))
        list.add(NewPromoModel(R.drawable.momo_vc2, "Uu dai vang", "san ngay"))
        list.add(NewPromoModel(R.drawable.momo_vc3, "Uu dai vang", "san ngay"))
        list.add(NewPromoModel(R.drawable.momo_vc1, "Uu dai vang", "san ngay"))
        list.add(NewPromoModel(R.drawable.momo_vc3, "Uu dai vang", "san ngay"))
        list.add(NewPromoModel(R.drawable.momo_vc2, "Uu dai vang", "san ngay"))

        val adapter = NewPromoAdapter(requireContext(), this)
        adapter.setData(list)
        binding.rcvNewPromo.adapter = adapter
        binding.rcvNewPromo.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun setUpInForNewsUpdate() {

        val list: ArrayList<NewUpdateModel> = ArrayList()

        list.add(
            NewUpdateModel(
                R.drawable.momo_voucher1,
                "u dai vang",
                "Tận hưởng lãi suất tiết kiệm lớn lên đến 20%",
                "Tan huong ngay"
            )
        )
        list.add(
            NewUpdateModel(
                R.drawable.momo_voucher2,
                "u dai vang",
                "Tận hưởng lãi suất tiết kiệm lớn lên đến 20%",
                "Tan huong ngay"
            )
        )
        list.add(
            NewUpdateModel(
                R.drawable.momo_voucher3,
                "u dai vang",
                "Tận hưởng lãi suất tiết kiệm lớn lên đến 20%",
                "Tan huong ngay"
            )
        )
        list.add(
            NewUpdateModel(
                R.drawable.momo_voucher4,
                "u dai vang",
                "Tận hưởng lãi suất tiết kiệm lớn lên đến 20%",
                "Tan huong ngay"
            )
        )
        list.add(
            NewUpdateModel(
                R.drawable.momo_voucher2,
                "u dai vang",
                "Tận hưởng lãi suất tiết kiệm lớn lên đến 20%",
                "Tan huong ngay"
            )
        )

        val adapter = NewsUpdateAdapter(requireContext(), this)
        adapter.setData(list)
        binding.rcvNewUpdate.adapter = adapter
        binding.rcvNewUpdate.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpInforMoMoSuggest() {

        val list: ArrayList<MomoSuggestModel> = ArrayList()

        list.add(MomoSuggestModel(R.drawable.ic_menu_listrik, getString(R.string.buy_film)))
        list.add(MomoSuggestModel(R.drawable.ic_riwayat, getString(R.string.thanh_toan)))
        list.add(MomoSuggestModel(R.drawable.logo_momo, getString(R.string.buy_film)))
        list.add(MomoSuggestModel(R.drawable.ic_riwayat, getString(R.string.thanh_toan)))
        list.add(MomoSuggestModel(R.drawable.ic_menu_listrik, getString(R.string.buy_film)))
        list.add(MomoSuggestModel(R.drawable.ic_riwayat, getString(R.string.thanh_toan)))

        val adapter = MomoSuggestAdapter(requireContext(), this)
        adapter.setData(list)
        binding.rcvMomoSuggest.adapter = adapter
        binding.rcvMomoSuggest.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

    }

    override fun onItemClick() {
        startActivity(Intent(requireActivity(), WebViewActivity::class.java))
    }

    override fun onCLickItemBuy() {
        startActivity(Intent(requireActivity(), BuyTicketActivity::class.java))
    }
}

interface OnItemVoucherCLick {
    fun onItemClick()
    fun onCLickItemBuy()
}
