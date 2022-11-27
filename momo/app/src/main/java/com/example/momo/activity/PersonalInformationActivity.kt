package com.example.momo.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.momo.common.Constant
import com.example.momo.databinding.ActivityPersonalInformationBinding
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class PersonalInformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonalInformationBinding
    val EDIT_PERSONAL = 101
    val EDIT_CONTACT = 102
    val EDIT_PRIVATE = 103

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        initData()
    }

    @SuppressLint("SetTextI18n")
    private fun initData() {
        val str = ""

        Log.e("====", Constant.userModel.toString())

        binding.tvName.text = Constant.userModel.name
        binding.tvPhoneNumber.text = "SDT: ${Constant.userModel.phoneNumber}"
        if (Constant.userModel.security[Constant.VERIFIED] as Boolean) binding.tvConfirm.text =
            "Da Xac thuc"
        else binding.tvConfirm.text = "Chua Xac thuc"

        binding.tvDob.text =
            "Date Of Birth: ${Constant.userModel.information[Constant.DOB]}"
        binding.tvGender.text =
            if (Constant.userModel.information[Constant.GENDER] == 1
            ) "Gender: Nam" else "Gender: Nu"
        binding.tvCardID.text =
            "CCCD/CMND: ${Constant.userModel.security[Constant.CARD_NUMBER]}"
        binding.tvAddress.text =
            "Address: ${Constant.userModel.information[Constant.ADDRESS]}"
        binding.tvMail.text =
            "Emall: ${Constant.userModel.information[Constant.EMAIL]}"
    }

    @SuppressLint("CheckResult")
    private fun setupView() {
        binding.ivBack.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            onBackPressed()
        }
        binding.tvDownloadQrcode.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {

        }
        binding.tvEditPersonal.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            startActivityForResult(
                Intent(
                    this@PersonalInformationActivity,
                    EditPersonalActivity::class.java
                ), EDIT_PERSONAL
            )
        }
        binding.tvEditContact.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            startActivityForResult(
                Intent(
                    this@PersonalInformationActivity,
                    EditContactActivity::class.java
                ), EDIT_CONTACT
            )
        }
        binding.tvEditPrivate.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            startActivityForResult(
                Intent(
                    this@PersonalInformationActivity,
                    EditPersonalActivity::class.java
                ), EDIT_PRIVATE
            )
        }
        binding.tvBlockList.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {

        }
        binding.tvFriendList.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {

        }
        binding.tvLogOut.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            finish()
        }
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            EDIT_PERSONAL -> {
                binding.tvDob.text =
                    "Date Of Birth: ${Constant.userModel.information[Constant.DOB]}"
                binding.tvGender.text =
                    if (Constant.userModel.information[Constant.GENDER] == 1
                    ) "Gender: Nam" else "Gender: Nu"
                binding.tvCardID.text =
                    "CCCD/CMND: ${Constant.userModel.security[Constant.CARD_NUMBER]}"
            }

            EDIT_CONTACT -> {
                binding.tvAddress.text =
                    "Address: ${Constant.userModel.information[Constant.ADDRESS]}"
                binding.tvMail.text =
                    "Emall: ${Constant.userModel.information[Constant.EMAIL]}"
            }

            EDIT_PRIVATE -> {

            }
        }
    }
}