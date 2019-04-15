package com.veeyikpong.fastnews

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.veeyikpong.fastnews.ui.main.MainActivity
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SampleSpinnerTest {

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(MainActivity::class.java, false)

    @Test
    fun clickSpinnerFirstItem() {

//        Espresso.onView(ViewMatchers.withId(R.id.spinner_data)).perform(ViewActions.click());

        onData(allOf(instanceOf(String::class.java))).atPosition(1).perform(ViewActions.click())
    }
}
