package com.example.momo.activity

import android.content.Intent
import android.widget.TextView
import androidx.test.rule.ActivityTestRule
import com.example.momo.R
import org.junit.Rule
import org.junit.Test


class AuthenticActivityTest {
    @get: Rule
    public var mActivityRule: ActivityTestRule<AuthenticActivity> =
        ActivityTestRule<AuthenticActivity>(AuthenticActivity::class.java, true, false)

    var intent = Intent()
    private val authenticActivity: AuthenticActivity = mActivityRule.launchActivity(intent)

    @Test
    fun setUpView() {
        authenticActivity.findViewById<TextView>(R.id.verifyOTPBtn).performClick()
    }
}