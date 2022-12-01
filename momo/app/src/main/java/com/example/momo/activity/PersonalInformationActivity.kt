package com.example.momo.activity

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.momo.R
import com.example.momo.common.Common
import com.example.momo.common.Constant
import com.example.momo.databinding.ActivityPersonalInformationBinding
import com.jakewharton.rxbinding3.view.clicks
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
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

        Glide.with(this).load(Constant.userModel.avatar).error(R.drawable.avatar)
            .into(binding.ivAvatar)
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

        binding.tvLog.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe() {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle(getString(R.string.are_you_sure_exit))
            dialog.setPositiveButton(getString(R.string.yes)) { dialog, which ->
                Common.setValidateUser(this@PersonalInformationActivity, "")
                startActivity(
                    Intent(
                        this@PersonalInformationActivity,
                        SplashActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                )
                dialog.dismiss()
                finish()
            }
            dialog.setNegativeButton(getString(R.string.no)) { dialog, which ->
                dialog.dismiss()
            }
            dialog.setCancelable(true)
            dialog.show()
        }

        binding.tvDownloadQrcode.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.qrcode_tcea)
            saveBitmapToMedia(bitmap, this)
            Toast.makeText(this, getString(R.string.save_to_media), Toast.LENGTH_SHORT).show()
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
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show()
        }
        binding.tvFriendList.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show()
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

    fun saveBitmapToMedia(bitmap: Bitmap, context: Context) {
        val root = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
            ).path
        )
        val file = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date()) + ".jpg"

        val filename = File(root, file)

        try {
            FileOutputStream(filename).use { out ->
                bitmap.compress(
                    Bitmap.CompressFormat.PNG,
                    100,
                    out
                )

            }
            val resolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.DATA, filename.path)
            }
            resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        } catch (e: IOException) {
            e.printStackTrace()
        }


    }
}