package com.example.momo.activity

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(
    MockitoJUnitRunner::class)
class LoginActivityTest {

    private var fakeString = "Login was successful"

    @Mock
    lateinit var introActivity: IntroActivity
    lateinit var mMockContext: Context

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun onCreate() {

    }

    @Test
    fun onStart() {
    }

//    @Test
//    fun readStringFromContext_LocalizedString() {
//        val myObjectUnderTest = LoginActivity()
//        val result: Boolean = myObjectUnderTest.checkNumberValidate("12356")
//        Assert.assertEquals(result, false)
//    }

    @Test
    fun `loginForCorrectData`(){
        val myObject = mock(LoginActivity::class.java)
        val result: Boolean = myObject.checkNumberValidate("123456789")
        Assert.assertEquals(result, false)
    }

    @Test
    fun `loginForErrorData`(){
        val myObject = mock(LoginActivity::class.java)
        val result: Boolean = myObject.checkNumberValidate("1")
        Assert.assertEquals(result, false)
    }

    @Test
    fun `loginForErrorData2`(){
        val myObject = mock(LoginActivity::class.java)
        val result: Boolean = myObject.checkNumberValidate("1234567890")
        Assert.assertEquals(result, false)
    }

    @Test
    fun `loginForErrorData3`(){
        val myObject = mock(LoginActivity::class.java)
        val result: Boolean = myObject.checkNumberValidate("")
        Assert.assertEquals(result, false)
    }

}