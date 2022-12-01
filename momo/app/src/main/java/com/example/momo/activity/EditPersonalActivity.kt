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
import com.example.momo.databinding.ActivityEditPersonalBinding
import com.example.momo.databinding.PopupPolicyBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class EditPersonalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditPersonalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPersonalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        initData()
    }

    private fun initData() {
        for (i in Constant.userModel.policy) {
            setPolicy(i.key, i.value)
        }

        binding.textInputNickname.setText(
            if (Constant.userModel.information[Constant.NICKNAME].toString() == "") Constant.userModel.information[Constant.NICKNAME].toString() else "Nickname"
        )
        if (Constant.userModel.information[Constant.DOB].toString() == "") {
            binding.textInputJob.isClickable = true
        } else {
            binding.textBirthday.setText(Constant.userModel.information[Constant.DOB].toString())
            binding.textInputJob.isClickable = false
        }
        binding.textInputGender.setText(if (Constant.userModel.information[Constant.GENDER] == 1) "Nam" else "Nu")

        binding.textInputRela.setText(
            if (Constant.userModel.information[Constant.RELATIONSHIP].toString() != "") Constant.userModel.information[Constant.RELATIONSHIP].toString() else "Relationship"
        )
        binding.textInputJob.setText(
            if (Constant.userModel.information[Constant.JOB].toString() != "") Constant.userModel.information[Constant.JOB].toString() else "Job"
        )
        binding.textInputHabit.setText(
            if (Constant.userModel.information[Constant.HABIT].toString() != "") Constant.userModel.information[Constant.HABIT].toString() else "Habit"
        )
        binding.textInputEdu.setText(
            if (Constant.userModel.information[Constant.EDUCATION].toString() != "") Constant.userModel.information[Constant.EDUCATION].toString() else "Education"
        )
    }

    private fun setPolicy(key: String, policy: Int) {
        when (key) {
            Constant.DOB -> {
                when (policy) {
                    1 -> {
                        binding.imBirthday.setImageResource(R.drawable.ic_resource_public)
                    }
                    2 -> {
                        binding.imBirthday.setImageResource(R.drawable.ic_friend)
                    }
                    3 -> {
                        binding.imBirthday.setImageResource(R.drawable.ic_resource_private)
                    }
                }
            }
            Constant.EDUCATION -> {
                when (policy) {
                    1 -> {
                        binding.imEdu.setImageResource(R.drawable.ic_resource_public)
                    }
                    2 -> {
                        binding.imEdu.setImageResource(R.drawable.ic_friend)
                    }
                    3 -> {
                        binding.imEdu.setImageResource(R.drawable.ic_resource_private)
                    }
                }
            }
            Constant.HABIT -> {
                when (policy) {
                    1 -> {
                        binding.imHabit.setImageResource(R.drawable.ic_resource_public)
                    }
                    2 -> {
                        binding.imHabit.setImageResource(R.drawable.ic_friend)
                    }
                    3 -> {
                        binding.imHabit.setImageResource(R.drawable.ic_resource_private)
                    }
                }
            }
            Constant.JOB -> {
                when (policy) {
                    1 -> {
                        binding.imJob.setImageResource(R.drawable.ic_resource_public)
                    }
                    2 -> {
                        binding.imJob.setImageResource(R.drawable.ic_friend)
                    }
                    3 -> {
                        binding.imJob.setImageResource(R.drawable.ic_resource_private)
                    }
                }
            }
            Constant.RELATIONSHIP -> {
                when (policy) {
                    1 -> {
                        binding.imRela.setImageResource(R.drawable.ic_resource_public)
                    }
                    2 -> {
                        binding.imRela.setImageResource(R.drawable.ic_friend)
                    }
                    3 -> {
                        binding.imRela.setImageResource(R.drawable.ic_resource_private)
                    }
                }
            }
            Constant.GENDER -> {
                when (policy) {
                    1 -> {
                        binding.imGender.setImageResource(R.drawable.ic_resource_public)
                    }
                    2 -> {
                        binding.imGender.setImageResource(R.drawable.ic_friend)
                    }
                    3 -> {
                        binding.imGender.setImageResource(R.drawable.ic_resource_private)
                    }
                }
            }
            Constant.NICKNAME -> {
                when (policy) {
                    1 -> {
                        binding.imNickname.setImageResource(R.drawable.ic_resource_public)
                    }
                    2 -> {
                        binding.imNickname.setImageResource(R.drawable.ic_friend)
                    }
                    3 -> {
                        binding.imNickname.setImageResource(R.drawable.ic_resource_private)
                    }
                }
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun setupView() {
        binding.ivBack.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            onBackPressed()
        }
        binding.tvSave.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            saveAndUpdateData()
        }
        binding.llPopupBirthday.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            openPopUpMenu(Constant.DOB, binding.llPopupBirthday, binding.imBirthday)
        }
        binding.llPopupEdu.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            openPopUpMenu(Constant.EDUCATION, binding.llPopupEdu, binding.imEdu)
        }
        binding.llPopupGender.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            openPopUpMenu(Constant.GENDER, binding.llPopupGender, binding.imGender)
        }
        binding.llPopupJob.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            openPopUpMenu(Constant.JOB, binding.llPopupJob, binding.imJob)
        }
        binding.llPopupHabit.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            openPopUpMenu(Constant.HABIT, binding.llPopupHabit, binding.imHabit)
        }
        binding.llPopupNickname.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            openPopUpMenu(Constant.NICKNAME, binding.llPopupNickname, binding.imNickname)
        }
        binding.llPopupRela.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            openPopUpMenu(Constant.RELATIONSHIP, binding.llPopupRela, binding.imRela)
        }

    }

    private fun saveAndUpdateData() {
        Constant.userModel.information[Constant.NICKNAME] =
            binding.textInputNickname.text.toString()
        Constant.userModel.information[Constant.RELATIONSHIP] =
            binding.textInputRela.text.toString()
        Constant.userModel.information[Constant.DOB] = binding.textBirthday.text.toString()
        Constant.userModel.information[Constant.JOB] = binding.textInputJob.text.toString()
        Constant.userModel.information[Constant.EDUCATION] = binding.textInputEdu.text.toString()
        Constant.userModel.information[Constant.HABIT] = binding.textInputHabit.text.toString()

        val data = Constant.getUserModelData(Constant.userModel)

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
            logo.setImageResource(R.drawable.ic_resource_public)
            popUpMenu.dismiss()
        }

        popupBinding.tvFriends.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            Constant.userModel.policy[key] = 2
            logo.setImageResource(R.drawable.ic_friend)
            popUpMenu.dismiss()
        }

        popupBinding.tvPrivate.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            Constant.userModel.policy[key] = 3
            logo.setImageResource(R.drawable.ic_resource_private)
            popUpMenu.dismiss()
        }

    }
}