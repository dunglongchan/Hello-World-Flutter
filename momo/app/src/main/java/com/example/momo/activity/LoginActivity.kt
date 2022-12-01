package com.example.momo.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.momo.R
import com.example.momo.common.Constant
import com.example.momo.databinding.ActivityLoginBinding
import com.example.momo.databinding.DialogRegisterBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var number: String
    private var isUserSign = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.tvNext.setOnClickListener {
            number = binding.textInput.text!!.trim().toString()
            if (!checkNumberValidate(number)) {
                Toast.makeText(this, getString(R.string.invalidate_number), Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (number.length == 10) number = number.substring(1)
            binding.tvNext.isClickable = false
            binding.textInput.isClickable = false
            binding.otpProgressBar.visibility = View.VISIBLE
            signUp()
        }

    }

    fun checkNumberValidate(number1: String): Boolean {
        return if (number1.length == 9 || number1.length == 10) {
            !(number1.length == 10 && number1.substring(0, 1) != "0")
        } else false
    }

    private fun sentOTP(number1: String) {
        val number2 = "+84$number1"
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number2)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    @SuppressLint("CheckResult")
    private fun signUp() {
        searchForUser()
    }

    private fun searchForUser() {
        FirebaseFirestore.getInstance().collection("user_data")
            .whereEqualTo("user_id", "user_84$number")
            .get()
            .addOnSuccessListener { r ->
                if (r.isEmpty) {
                    isUserSign = false
                    showDialogRegister()
                } else {
                    for (document in r) Constant.userModel =
                        Constant.castDataToUserModel(document.data)
                    isUserSign = true
                    sentOTP(number)
                }
            }
            .addOnFailureListener {
                Log.e("====", it.message.toString())
            }
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    private fun showDialogRegister() {
        binding.otpProgressBar.visibility = View.GONE
        val dialog = Dialog(this@LoginActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val dialogBinding = DialogRegisterBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        val window = dialog.window!!
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        dialog.show()

        dialogBinding.tvTitle.text = "${getString(R.string.register_momo)}-$number"

        dialogBinding.tvChangeNumber.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            number = ""
            binding.textInput.text?.clear()
            dialog.dismiss()
        }

        dialogBinding.tvConfirm.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            sentOTP(number)
            dialog.dismiss()
        }

        dialog.setOnDismissListener {
            binding.tvNext.isClickable = true
            binding.textInput.isClickable = true
        }
    }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    sendToMain()
                } else {
                    Log.d("TAG", "signInWithPhoneAuthCredential: ${task.exception.toString()}")
                }
            }
    }

    private fun sendToMain() {
        startActivity(
            Intent(this, AuthenticActivity::class.java).putExtra(
                Constant.IS_USER_SIGNED,
                isUserSign
            )
        )
        finish()
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            if (e is FirebaseAuthInvalidCredentialsException) {
                Log.d("TAG", "onVerificationFailed: ${e.toString()}")
            } else if (e is FirebaseTooManyRequestsException) {
                Log.d("TAG", "onVerificationFailed: ${e.toString()}")
            }
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            if (isUserSign) {
                val intent = Intent(this@LoginActivity, AuthenticActivity::class.java)
                intent.putExtra("OTP", verificationId)
                intent.putExtra("resendToken", token)
                intent.putExtra("phoneNumber", number)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                intent.putExtra("OTP", verificationId)
                intent.putExtra("resendToken", token)
                intent.putExtra("phoneNumber", number)
                startActivityForResult(intent, 101)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            number = ""
            binding.textInput.text?.clear()
        }
    }

}