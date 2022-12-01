package com.example.momo.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.momo.R
import com.example.momo.activity.PersonalInformationActivity
import com.example.momo.activity.SplashActivity
import com.example.momo.activity.TransactionManagerActivity
import com.example.momo.common.Common
import com.example.momo.common.Constant
import com.example.momo.databinding.FragmentProfileBinding
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun getViewBinding(): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(layoutInflater)
    }

    @SuppressLint("CheckResult", "UseCompatLoadingForDrawables")
    override fun setup() {
        binding.tvName.text = Constant.userModel.name
        binding.tvPhonenumber.text = Constant.userModel.phoneNumber
        Glide.with(this).load(Constant.userModel.avatar).error(R.drawable.avatar)
            .into(binding.imgAvatar)

        binding.llProfile.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            startActivity(Intent(requireContext(), PersonalInformationActivity::class.java))
        }

        binding.tvShareapp.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            shareApp()
        }

        binding.llManager.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            startActivity(Intent(requireContext(), TransactionManagerActivity::class.java))
        }

        binding.llFeedback.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:")
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("dunglccl@icloud.com"))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback Momo vip pro")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }

        binding.llRate.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            Common.showRate(requireContext())
        }

        binding.llLanguage.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            val dialog = AlertDialog.Builder(requireContext())
            dialog.setTitle(getString(R.string.language))
            dialog.setPositiveButton(getString(R.string.english)) { dialog, which ->
                Common.setLocale(requireContext(), "en")
                startActivity(
                    Intent(
                        requireContext(),
                        SplashActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                )
                dialog.dismiss()
            }
            dialog.setNegativeButton(getString(R.string.vietnamese)) { dialog, which ->
                Common.setLocale(requireContext(), "vi")
                startActivity(
                    Intent(
                        requireContext(),
                        SplashActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                )
                dialog.dismiss()
            }

            dialog.setCancelable(true)
            dialog.show()
        }

    }

    private fun shareApp() {

        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Momo qua vip pro - https://play.google.com/store/apps/details?id=com.mservice.momotransfer"
        )
        startActivity(Intent.createChooser(shareIntent, "Share to"))
    }
}
