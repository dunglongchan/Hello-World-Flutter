package com.example.momo.activity

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import com.example.momo.R
import com.example.momo.common.Constant
import com.example.momo.databinding.ActivityEditContactBinding
import com.example.momo.databinding.PopupPolicyBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class EditContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
        initData()
    }

    private fun initData() {
        for (i in Constant.userModel.policy) {
            setPolicy(i.key, i.value)
        }

        binding.textInputAddress.setText(
            if (Constant.userModel.information[Constant.ADDRESS].toString() != "") Constant.userModel.information[Constant.ADDRESS].toString() else "Address"
        )
        binding.textInputEmail.setText(
            if (Constant.userModel.information[Constant.EMAIL].toString() != "") Constant.userModel.information[Constant.EMAIL].toString() else "Email"
        )
    }

    private fun setPolicy(key: String, value: Int) {
        when (key) {
            Constant.ADDRESS -> {
                when (value) {
                    1 -> {
                        binding.imAddress.setImageResource(R.drawable.english)
                    }
                    2 -> {
                        binding.imAddress.setImageResource(R.drawable.ic_dmeo)
                    }
                    3 -> {
                        binding.imAddress.setImageResource(R.drawable.ic_game)
                    }
                }
            }
            Constant.EMAIL -> {
                when (value) {
                    1 -> {
                        binding.imEmail.setImageResource(R.drawable.english)
                    }
                    2 -> {
                        binding.imEmail.setImageResource(R.drawable.ic_dmeo)
                    }
                    3 -> {
                        binding.imEmail.setImageResource(R.drawable.ic_game)
                    }
                }
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun setUpView() {
        binding.ivBack.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            onBackPressed()
        }
        binding.tvSave.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            saveAndUpdateData()
        }
        binding.llPopupAddress.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            openPopUpMenu(Constant.DOB, binding.llPopupAddress, binding.imAddress)
        }
        binding.llPopupEmail.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            openPopUpMenu(Constant.EDUCATION, binding.llPopupEmail, binding.imEmail)
        }
    }

    private fun saveAndUpdateData() {
        Constant.userModel.information[Constant.EMAIL] =
            binding.textInputEmail.text.toString()
        Constant.userModel.information[Constant.ADDRESS] =
            binding.textInputAddress.text.toString()

        val data = Constant.getUserModelData()

        FirebaseFirestore.getInstance().collection("user_data")
            .document(Constant.userModel.user_id).update(data)
        finish()
    }

    @SuppressLint("CheckResult")
    private fun openPopUpMenu(key: String, view: View, logo: ImageView) {
        val popUpMenu = PopupWindow(this)
        val popupBinding = PopupPolicyBinding.inflate(layoutInflater)
        popUpMenu.contentView = popupBinding.root

        popUpMenu.setBackgroundDrawable(ColorDrawable())
        popUpMenu.showAsDropDown(view)
        popUpMenu.isOutsideTouchable = true

        popupBinding.tvPublic.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            Constant.userModel.policy[key] = 1
            logo.setImageResource(R.drawable.english)
            popUpMenu.dismiss()
        }

        popupBinding.tvFriends.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            Constant.userModel.policy[key] = 2
            logo.setImageResource(R.drawable.ic_dmeo)
            popUpMenu.dismiss()
        }

        popupBinding.tvPrivate.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            Constant.userModel.policy[key] = 3
            logo.setImageResource(R.drawable.ic_game)
            popUpMenu.dismiss()
        }

    }
}