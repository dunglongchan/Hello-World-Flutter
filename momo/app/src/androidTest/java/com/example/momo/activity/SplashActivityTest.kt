package com.example.momo.activity

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    @get: Rule
    public var mActivityRule: ActivityTestRule<SplashActivity> =
        ActivityTestRule<SplashActivity>(SplashActivity::class.java, true, false)

    var intent = Intent()
    private val splashActivity: SplashActivity = mActivityRule.launchActivity(intent)

    @Test
    fun testSplashActivity() {
        mActivityRule.launchActivity(null)
    }
}