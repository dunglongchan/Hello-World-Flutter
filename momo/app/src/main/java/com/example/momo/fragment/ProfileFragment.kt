package com.example.momo.fragment

import android.annotation.SuppressLint
import android.content.Intent
import com.bumptech.glide.Glide
import com.example.momo.activity.LoginActivity
import com.example.momo.common.Constant
import com.example.momo.databinding.FragmentProfileBinding
import com.example.momo.model.UserModel
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun getViewBinding(): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(layoutInflater)
    }

    @SuppressLint("CheckResult")
    override fun setup() {
        binding.tvName.text = Constant.userModel.name
        binding.tvPhonenumber.text = Constant.userModel.phoneNumber
        Glide.with(this).load(Constant.userModel.avatar).into(binding.imgAvatar)
        binding.tvLogout.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            Constant.userModel = UserModel("", "")
            startActivity(
                Intent(
                    requireContext(),
                    LoginActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        }
        binding.llProfile.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {

        }
    }
}
