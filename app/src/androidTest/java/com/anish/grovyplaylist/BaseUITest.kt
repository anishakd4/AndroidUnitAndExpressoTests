package com.anish.grovyplaylist

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import androidx.test.espresso.IdlingResource
import com.anish.grovyplaylist.playlist.client
import com.jakewharton.espresso.OkHttp3IdlingResource

@RunWith(AndroidJUnit4::class)
abstract class BaseUITest {

//    val idlingResource: OkHttp3IdlingResource = OkHttp3IdlingResource.create("okhttp", client)
//
//    @Before
//    fun setup() {
//        IdlingRegistry.getInstance().register
//    }
//
//    @After
//    fun tearDown() {
//        IdlingRegistry.getInstance().unregister(idlingResource)
//    }
}