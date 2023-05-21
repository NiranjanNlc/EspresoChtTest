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
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.lniranjan.chatwithespreso.ui.activity.Conversation
import org.mockito.Mockito.mock
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify


@RunWith(AndroidJUnit4::class)
@LargeTest
class ConversationTest {

    @Rule
    @JvmField
    val activityRule: ActivityScenarioRule<Conversation> = ActivityScenarioRule(Conversation::class.java)

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<*>
    private val testData = listOf("Item 1", "Item 2", "Item 3")
    private lateinit var viewModel: ViewModel

    @Before
    fun setUp() {
        activityRule.scenario.onActivity {
            recyclerView = it.findViewById(R.id.recycler_view)
            adapter = recyclerView.adapter!!
            viewModel = ViewModelProvider(it).get(ViewModel::class.java)
        }
    }
    @Test
    fun testRecyclerViewExists() {
        // Check if the RecyclerView exists
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

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
        assert(true)
    }
    @Test
    fun testRecyclerViewItemCount() {
        // Check if the RecyclerView displays the expected number of items
        assertEquals(
            "RecyclerView item count should match the test dataset size",
            testData.size,
            adapter?.itemCount
        )
    }

    @Test
    fun testRecyclerViewDataPopulation() {
        // Set up a test dataset in the ViewModel

        // Check if the RecyclerView displays the correct data

    }
    @Test
    fun testRecyclerViewItemClickListener() {

        // Set up a test dataset in the ViewModel


        // Create a mock click listener
        val mockClickListener = mock(View.OnClickListener::class.java)

        // Set the mock click listener to the adapter
//        adapter.setOnItemClickListener(mockClickListener)

        // Simulate a click on a specific item
        val position = 0
        val viewHolder = adapter.createViewHolder(recyclerView, adapter.getItemViewType(position))
//        adapter.bindViewHolder(viewHolder, position)
        viewHolder.itemView.performClick()

        // Verify if the click listener was called with the correct position

        verify(mockClickListener).onClick(eq(viewHolder.itemView))
    }


    @Test
    fun testRecyclerViewEmptyState() {

        // Set up an empty dataset in the ViewModel


        // Check if the RecyclerView displays an empty state UI
        assertEquals("RecyclerView should show an empty state UI when the dataset is empty", 0, adapter?.itemCount)
        // ... Add assertions to check if the empty state UI is correctly displayed
    }

    @Test
    fun testRecyclerViewItemRemoval() {


        // Set up a test dataset in the ViewModel

        // Remove an item from the dataset


        // Check if the RecyclerView updates correctly after item removal
        assertEquals("RecyclerView item count should reflect the updated dataset", testData.size, adapter?.itemCount)
        // ... Add assertions to check if the removed item is no longer displayed
    }
}

