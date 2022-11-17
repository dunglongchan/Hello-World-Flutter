package com.example.momo.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.momo.R
import com.example.momo.common.Constant
import com.example.momo.databinding.ActivityRegisterBinding
import com.example.momo.databinding.DialogChangeNumberBinding
import com.example.momo.model.UserModel
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var OTP: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var phoneNumber: String
    private var wrongCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        OTP = intent.getStringExtra("OTP").toString()
        resendToken = intent.getParcelableExtra("resendToken")!!
        phoneNumber = intent.getStringExtra("phoneNumber")!!

        auth = FirebaseAuth.getInstance()

        setupView()
    }

    @SuppressLint("CheckResult")
    private fun setupView() {

        binding.tvContent.text = "Mã xác thực 6 số đã được gửi đến 84$phoneNumber"

        binding.otpEditText1.requestFocus()
        addTextChangeListener()
        resendOTPTvVisibility()

        binding.resendTextView.setOnClickListener {
            resendVerificationCode()
            resendOTPTvVisibility()
        }

        binding.tvChangeNumber.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            finish()
        }

        binding.verifyOTPBtn.setOnClickListener {
            val typedOTP =
                (binding.otpEditText1.text.toString() + binding.otpEditText2.text.toString() + binding.otpEditText3.text.toString()
                        + binding.otpEditText4.text.toString() + binding.otpEditText5.text.toString() + binding.otpEditText6.text.toString())

            if (typedOTP.isNotEmpty()) {
                if (typedOTP.length == 6) {
                    val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                        OTP, typedOTP
                    )
                    signInWithPhoneAuthCredential(credential)
                } else {
                    Toast.makeText(this, "Please Enter Correct OTP", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please Enter OTP", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun resendOTPTvVisibility() {
        binding.otpEditText1.setText("")
        binding.otpEditText2.setText("")
        binding.otpEditText3.setText("")
        binding.otpEditText4.setText("")
        binding.otpEditText5.setText("")
        binding.otpEditText6.setText("")
        binding.resendTextView.visibility = View.INVISIBLE
        binding.resendTextView.isEnabled = false

        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            binding.resendTextView.visibility = View.VISIBLE
            binding.resendTextView.isEnabled = true
        }, 60000)
    }

    private fun resendVerificationCode() {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+84$phoneNumber")       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)
            .setForceResendingToken(resendToken)// OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential)
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
            OTP = verificationId
            resendToken = token
        }
    }

    internal fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Authenticate Successfully", Toast.LENGTH_SHORT).show()
                    sendToMain()
                } else {
                    // Sign in failed, display a message and update the UI
//                    Log.d("TAG", "signInWithPhoneAuthCredential: ${task.exception.toString()}")
//                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
//                    }
                    wrongCount++
                    binding.tvError.text =
                        "Mã xác thực không chính xác, bạn còn ${3 - wrongCount} lần thử"
                    if (wrongCount > 2) {
                        showErrorDialog()
                    }
                }
            }
    }

    @SuppressLint("CheckResult")
    private fun showErrorDialog() {
        val dialog = Dialog(this@RegisterActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val dialogBinding = DialogChangeNumberBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        val window = dialog.window!!
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        dialog.show()

        dialogBinding.tvCancel.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            dialog.dismiss()
        }

        dialogBinding.tvConfirm.clicks().throttleFirst(1, TimeUnit.SECONDS).subscribe {
            dialog.dismiss()
            finish()
        }


    }

    private fun sendToMain() {
        Constant.userModel = UserModel("user_84$phoneNumber")
        startActivity(Intent(this, CreatePasswordActivity::class.java))
        finish()
    }

    private fun addTextChangeListener() {
        binding.otpEditText1.addTextChangedListener(EditTextWatcher(binding.otpEditText1))
        binding.otpEditText2.addTextChangedListener(EditTextWatcher(binding.otpEditText2))
        binding.otpEditText3.addTextChangedListener(EditTextWatcher(binding.otpEditText3))
        binding.otpEditText4.addTextChangedListener(EditTextWatcher(binding.otpEditText4))
        binding.otpEditText5.addTextChangedListener(EditTextWatcher(binding.otpEditText5))
        binding.otpEditText6.addTextChangedListener(EditTextWatcher(binding.otpEditText6))
    }

    inner class EditTextWatcher(private val view: View) : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {

            val text = p0.toString()
            when (view.id) {
                R.id.otpEditText1 -> if (text.length == 1) binding.otpEditText2.requestFocus()
                R.id.otpEditText2 -> if (text.length == 1) binding.otpEditText3.requestFocus() else if (text.isEmpty()) binding.otpEditText1.requestFocus()
                R.id.otpEditText3 -> if (text.length == 1) binding.otpEditText4.requestFocus() else if (text.isEmpty()) binding.otpEditText2.requestFocus()
                R.id.otpEditText4 -> if (text.length == 1) binding.otpEditText5.requestFocus() else if (text.isEmpty()) binding.otpEditText3.requestFocus()
                R.id.otpEditText5 -> if (text.length == 1) binding.otpEditText6.requestFocus() else if (text.isEmpty()) binding.otpEditText4.requestFocus()
                R.id.otpEditText6 -> if (text.isEmpty()) binding.otpEditText5.requestFocus()

            }
        }

    }
}