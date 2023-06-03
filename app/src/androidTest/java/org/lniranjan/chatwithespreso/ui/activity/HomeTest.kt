package org.lniranjan.chatwithespreso.ui.activity

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.lniranjan.chatwithespreso.databinding.ActivityHomeBinding

class HomeTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(Home::class.java)

    private lateinit var binding: ActivityHomeBinding


    fun setUp() {
        activityRule.scenario.onActivity {
            binding = it.binding
        }
    }


    @Test
    fun viewPagerWithExpectedNumberOfPages() {
        val expectedNumberOfPages = 2
        onView(withId(binding.viewPager.id))
            .check(matches(hasItemCount(expectedNumberOfPages)))
    }

    @Test
    fun tabLayoutWithCorrectItems() {
        onView(withTabTextAtPosition(0, "Chats"))
            .check(matches(isDisplayed()))
        onView(withTabTextAtPosition(1, "Contacts"))
            .check(matches(isDisplayed()))
        onView(withTabTextAtPosition(2, "Settings"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun viewPagerSwipe() {
        // Swipe to the next page
        onView(withId(binding.viewPager.id))
            .perform(swipeViewPagerToPosition(1))
        onView(withTabText("Contacts"))
            .check(matches(isDisplayed()))

        // Swipe to the previous page
        onView(withId(binding.viewPager.id))
            .perform(swipeViewPagerToPosition(0))
        onView(withTabText("Chats"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun clickOnTabChangesViewPagerPage() {
        // Click on the "Contacts" tab
        onView(withTabText("Contacts"))
            .perform(click())
        onView(withTabText("Contacts"))
            .check(matches(isSelected()))
        onView(withId(binding.viewPager.id))
            .check(matches(withCurrentItem(1)))

        // Click on the "Chats" tab
        onView(withTabText("Chats"))
            .perform(click())
        onView(withTabText("Chats"))
            .check(matches(isSelected()))
        onView(withId(binding.viewPager.id))
            .check(matches(withCurrentItem(0)))
    }

    @Test
    fun swipeViewPagerChangesSelectedTab() {
        // Swipe to the next page
        onView(withId(binding.viewPager.id))
            .perform(swipeLeft())
        onView(withTabText("Contacts"))
            .check(matches(isSelected()))

        // Swipe to the previous page
        onView(withId(binding.viewPager.id))
            .perform(swipeRight())
        onView(withTabText("Chats"))
            .check(matches(isSelected()))
    }

    //extension function
    private fun hasItemCount(expectedCount: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                description?.appendText("with item count: $expectedCount")
            }

            override fun matchesSafely(item: View?): Boolean {
                if (item is ViewPager2) {
                    return item.adapter?.itemCount == expectedCount
                }
                return false
            }
        }
    }


    private fun withTabTextAtPosition(position: Int, expectedText: String): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                description?.appendText("at position $position with tab text: $expectedText")
            }

            override fun matchesSafely(item: View?): Boolean {
                if (item is TabLayout) {
                    val tab = item.getTabAt(position)
                    return tab != null && tab.text == expectedText
                }
                return false
            }
        }
    }


    private fun withTabText(expectedText: String): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                description?.appendText("with tab text: $expectedText")
            }

            override fun matchesSafely(item: View?): Boolean {
                if (item is TabLayout.TabView) {
                    val tab = item.tab
                    return tab != null && tab.text == expectedText
                }
                return false
            }
        }
    }

    private fun swipeViewPagerToPosition(position: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(ViewPager2::class.java)
            }

            override fun getDescription(): String {
                return "Swipe ViewPager to position: $position"
            }

            override fun perform(uiController: UiController?, view: View?) {
                val viewPager = view as ViewPager2
                viewPager.setCurrentItem(position, true)
                uiController?.loopMainThreadUntilIdle()
            }
        }
    }
    private fun withCurrentItem(expectedItem: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                description?.appendText("with current item: $expectedItem")
            }

            override fun matchesSafely(item: View?): Boolean {
                if (item is ViewPager2) {
                    return item.currentItem == expectedItem
                }
                return false
            }
        }
    }


}