package org.lniranjan.chatwithespreso

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.lniranjan.chatwithespreso.ui.activity.Conversation


@RunWith(AndroidJUnit4::class)
class ConversationTest {

    @Rule
    @JvmField
    val activityRule : ActivityScenarioRule<Conversation> = ActivityScenarioRule(Conversation::class.java)
    @Test
    fun testSendMessage() {
        val message = "Hello, World!" // Message to be sent
        // Type the message into the TextInputEditText
        onView(withId(R.id.text_input)).perform(typeText(message))
        closeSoftKeyboard()
        // Click the send button
        onView(withId(R.id.button_send)).perform(click())
        // Check if the message is displayed in the RecyclerView
        onView(withId(R.id.recycler_view)).check(matches(hasDescendant(withText(message))))
    }
}
