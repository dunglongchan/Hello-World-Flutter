package com.example.momo.activity

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class IntroActivityTest {

    @Test
    fun startActivity() {

    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

//    @Before
//    fun init(){
//        PowerMockito.mockStatic(DataBindingUtil::class.java);
//        PowerMockito.mockStatic(ViewDataBinding::class.java);
//    }

//    @Rule
//    var mMainActivityRule: ActivityTestRule = ActivityTestRule(
//        MainActivity::class.java
//    )


//    @Test
//    fun testIntro() {
//        val myObject = mock(IntroActivity::class.java)
////        val v: View = myObject.findViewById<TextView>(R.id.tv_next)
////        myObject.binding.tvNext.performClick()performClick
//        assertNull(myObject.binding.tvNext)
//        assertTrue(myObject.isActivityTransitionRunning);
//    }
}