package com.wisnu.footballs.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.wisnu.footballs.R
import com.wisnu.footballs.league.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerView() {
        onView(withId(R.id.rv_league))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_league)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10)
        )
        onView(withId(R.id.rv_league)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click())
        )
    }

    @Test
    fun testLeagueBehaviour() {
        onView(withId(R.id.bottom_nav)).check(matches(isDisplayed()))
    }
}