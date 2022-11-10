package com.example.momo.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.momo.common.Constant
import com.example.momo.databinding.ActivityLoginBinding
import com.example.momo.model.UserModel
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
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
            if (!checkNumberValidate(number)) return@setOnClickListener
            if (number.length == 10) number = number.substring(1)
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

    private fun signUp() {

        sentOTP(number)

        val userID = "user_"
        var userModel: UserModel
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        firestore.collection("user_data")
            .get()
            .addOnSuccessListener { r ->
                for (document in r) {
                    if (document.id == userID + "84$number") {
                        userModel = Constant.castDataToUserModel(document.data)
                        Constant.userModel = userModel
                        isUserSign = true
                        break
                    }
                }
            }
            .addOnFailureListener {
                print(it.message)
            }
        if (userID == "user_") {
            userModel = UserModel(userID + "84$number")
            Constant.userModel = userModel
        }
    }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
//                    Toast.makeText(this, "Authenticate Successfully", Toast.LENGTH_SHORT).show()
                    sendToMain()
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.d("TAG", "signInWithPhoneAuthCredential: ${task.exception.toString()}")
//                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
//                        // The verification code entered was invalid
//                    }
                    // Update UI
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
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                Log.d("TAG", "onVerificationFailed: ${e.toString()}")
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Log.d("TAG", "onVerificationFailed: ${e.toString()}")
            }
            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            // Save verification ID and resending token so we can use them later
            val intent = Intent(this@LoginActivity, AuthenticActivity::class.java)
            intent.putExtra("OTP", verificationId)
            intent.putExtra("resendToken", token)
            intent.putExtra(Constant.IS_USER_SIGNED, isUserSign)
            intent.putExtra("phoneNumber", number)
            startActivity(intent)
//            finish()
        }
    }

}