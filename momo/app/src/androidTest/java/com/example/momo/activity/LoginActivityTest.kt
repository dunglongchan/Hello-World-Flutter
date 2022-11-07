package com.example.momo.activity

import android.content.Intent
import android.widget.TextView
import androidx.test.rule.ActivityTestRule
import com.example.momo.R
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {

    @get: Rule
    public var mActivityRule: ActivityTestRule<LoginActivity> =
        ActivityTestRule<LoginActivity>(LoginActivity::class.java,true,false)

    var intent = Intent()
    private val loginActivity: LoginActivity = mActivityRule.launchActivity(intent)

    @Test
    fun checkValidateNumber() {
        assertTrue(loginActivity.checkNumberValidate("123456789"))
        assertFalse(loginActivity.checkNumberValidate(""))
        assertFalse(loginActivity.checkNumberValidate("1012345116789"))
        assertFalse(loginActivity.checkNumberValidate("10156789"))
    }

    @Test
    fun signWithPhoneAuthCredential(){

    }

    @Test
    fun performClick(){
        loginActivity.findViewById<TextView>(R.id.tv_next).performClick()
    }

    @Test
    fun sentToMain(){
        mActivityRule.launchActivity(null)
    }
}