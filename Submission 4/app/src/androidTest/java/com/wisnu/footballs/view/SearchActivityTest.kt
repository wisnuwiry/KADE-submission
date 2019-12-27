package com.wisnu.footballs.view

import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.wisnu.footballs.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(SearchActivity::class.java)

    @Test
    fun testSearchBehaviour() {
//        check searchBar
        onView(withId(R.id.search_action)).check(matches(isDisplayed()))

        onView(withId(R.id.search_action)).perform(click())
//        example search on SearchBar
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(typeText("bar"))

        Thread.sleep(3000)

//        check recyclerView
        onView(withId(R.id.rv_event))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rv_event)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                1
            )
        )

//        check item recyclerView to detail EventActivity
        onView(withId(R.id.rv_event)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click())
        )

//        check favoriteButton available on detail Event
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
//        add favorite or unFavorite
        onView(withId(R.id.add_to_favorite)).perform(click())
    }
}