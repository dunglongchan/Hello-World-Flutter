package com.example.momo.activity

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(
    MockitoJUnitRunner::class)
class MainActivityTest {

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testForViewPager(){
        val myObject = mock(MainActivity::class.java)
    }

}