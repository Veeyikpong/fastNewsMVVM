package com.veeyikpong.threefragmentsexample

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers
import org.junit.Rule


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(MainActivity::class.java,false)

    @Test
    fun clickSpinnerFirstItem(){

        Espresso.onView(ViewMatchers.withId(R.id.spinner_data)).perform(ViewActions.click());

        onData(allOf(instanceOf(String::class.java))).atPosition(1).perform(ViewActions.click())
    }
}
