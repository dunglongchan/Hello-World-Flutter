package com.example.momo.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.momo.common.Common
import com.example.momo.common.Constant
import com.example.momo.databinding.ActivitySplashBinding
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent()
        if (Common.getValidateUser(this).isEmpty()) {
            intent.setClass(this@SplashActivity, IntroActivity::class.java)
        } else {
            Constant.userModel.user_id = Common.getValidateUser(this)
            FirebaseFirestore.getInstance().collection("user_data")
                .document(Constant.userModel.user_id).get().addOnSuccessListener {
                    if (it.data != null) {
                        Constant.userModel = Constant.castDataToUserModel(it.data!!)
                    }
                }
            intent.setClass(this@SplashActivity, EnterPassWordActivity::class.java)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, 1500)
    }
}