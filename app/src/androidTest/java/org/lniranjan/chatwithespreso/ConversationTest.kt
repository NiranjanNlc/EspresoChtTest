package org.lniranjan.chatwithespreso

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.lniranjan.chatwithespreso.databinding.ActivityConversationBinding
import org.lniranjan.chatwithespreso.modal.Message
import org.lniranjan.chatwithespreso.ui.activity.Conversation
import org.lniranjan.chatwithespreso.ui.viewmodal.ConversationViewModal
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@RunWith(AndroidJUnit4::class)
@LargeTest
class ConversationTest {

    @Rule
    @JvmField
    val activityRule: ActivityScenarioRule<Conversation> = ActivityScenarioRule(Conversation::class.java)

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding : ActivityConversationBinding
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var viewModel: ConversationViewModal
    private lateinit var testMessages: MutableList<Message>
    @Before
    fun setUp() {
        activityRule.scenario.onActivity {
            binding = it.binding
            recyclerView = binding.messagesView
            viewModel = it.viewModal
            adapter= it.adapter
        }
    }
    @Test
    fun testRecyclerViewExists() {
        // Check if the RecyclerView exists
        onView(withId(binding.messagesView.id)).check(matches(isDisplayed()))
    }

    @Test
    fun testSendMessage() {
        val message = "Hello, World!" // Message to be sent
        // Type the message into the TextInputEditText
        onView(withId(binding.textInput.id)).perform(typeText(message))
        closeSoftKeyboard()
        // Click the send button
        onView(withId(binding.buttonSend.id)).perform(click())
        // Check if the message is displayed in the RecyclerView
        onView(withId(recyclerView.id)).check(matches(hasDescendant(withText(message))))
        assert(true)
    }
    @Test
    fun testRecyclerViewItemCount() {
        testMessages = mutableListOf(
            Message(message = "Message 1"),
            Message(message = "Message 2"),
            Message(message = "Message 3")
        )
        viewModel._messageList.postValue(testMessages)
        // Check if the RecyclerView displays the expected number of items
        Thread.sleep(1000)
        assertEquals(
            "RecyclerView item count should match the test dataset size",
            testMessages.size,
            adapter?.itemCount
        )
    }

    @Test
    fun testRecyclerViewDataPopulation() {
        testMessages = mutableListOf(
            Message(message = "Message 1"),
            Message(message = "Message 2"),
            Message(message = "Message 3")
        )
        viewModel._messageList.postValue(testMessages)
        Thread.sleep(1000)
        // Check if the RecyclerView displays the correct data
        onView(withId(binding.messagesView.id))
            .check(matches(hasDescendant(withText("Message 1"))))
        onView(withId(binding.messagesView.id))
            .check(matches(hasDescendant(withText("Message 2"))))
        onView(withId(binding.messagesView.id))
            .check(matches(hasDescendant(withText("Message 3"))))

    }

    @Test
    fun testRecyclerViewEmptyState() {
        // Set up an empty dataset in the ViewModel
        viewModel.deleteAllMessages()
        // Check if the RecyclerView displays an empty state UI
        assertEquals("RecyclerView should show an empty state UI when the dataset is empty", 0, adapter?.itemCount)
        // ... Add assertions to check if the empty state UI is correctly displayed
    }
    @Test
    fun testRecyclerViewItemRemoval() {
        testMessages = mutableListOf(
            Message(message = "Message 1"),
            Message(message = "Message 2"),
            Message(message = "Message 3")
        )
        // Wait for the data to be updated
        Thread.sleep(1000)
        // Remove an item from the dataset
        val position = 0
        testMessages.removeAt(position)
        viewModel._messageList.postValue(testMessages)

        // Wait for the data to be updated
        Thread.sleep(1000)

        // Check if the RecyclerView updates correctly after item removal
        onView(withId(binding.messagesView.id))
            .check(matches(not(hasDescendant(withText("Message 1")))))
        onView(withId(binding.messagesView.id))
            .check(matches(hasDescendant(withText("Message 2"))))
        onView(withId(binding.messagesView.id))
            .check(matches(hasDescendant(withText("Message 3"))))
        assertEquals(testMessages.size, adapter.itemCount)
    }
    @Test
    fun testRecyclerViewItemClickListener() {

  // future enhancement
        assert(true)
    }

}

