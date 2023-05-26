package org.lniranjan.chatwithespreso.ui.activity

import android.content.Context
import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.lniranjan.chatwithespreso.databinding.ActivityProfileBinding
import org.lniranjan.chatwithespreso.ui.viewmodal.ProfileViewModal


@RunWith(AndroidJUnit4::class)
@LargeTest
class ProfileActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(ProfileActivity::class.java)
    
    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileViewModal

    private lateinit var activity: ProfileActivity

    @Before
    fun setUp() {
        // Use the 'testing' Context
        // Use the 'testing' Context
        val context: Context = InstrumentationRegistry.getInstrumentation().getTargetContext()
        context.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
        activityRule.scenario.onActivity {
             binding = it.binding
            viewModel = it.viewModal
            activity = it
        }
    }

    @Test
    fun testEditTextFieldsDisplayed() {
        Espresso.onView(ViewMatchers.withId(binding.setUserName.id))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(binding.setBio.id))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testEditTextFieldsEditableOnEditMode() {
        Espresso.onView(ViewMatchers.withId(binding.updateSettingsBtn.id))
            .perform(ViewActions.click())
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(binding.setUserName.id))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
        Espresso.onView(ViewMatchers.withId(binding.setBio.id))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    @Test
    fun testEditTextFieldsNonEditableOnNonEditMode() {
        Espresso.onView(ViewMatchers.withId(binding.setUserName.id))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
        Espresso.onView(ViewMatchers.withId(binding.setBio.id))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
    }

    @Test
    fun testSaveButtonDisplayedOnEditMode() {
        Espresso.onView(ViewMatchers.withId(binding.updateSettingsBtn.id))
            .perform(ViewActions.click())
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(binding.updateSettingsBtn.id))
            .check(ViewAssertions.matches(ViewMatchers.withText("Save Profile")))
    }

    @Test
    fun testUpdateButtonDisplayedOnNonEditMode() {
        Espresso.onView(ViewMatchers.withId(binding.updateSettingsBtn.id))
            .check(ViewAssertions.matches(ViewMatchers.withText("Edit Profile")))
    }
    @Test
    fun testUserNameEditTextContent() {
        // Click the Update button
        Espresso.onView(ViewMatchers.withId(binding.updateSettingsBtn.id))
            .perform(ViewActions.click())
        Thread.sleep(1000)
        val testUserName = "John Doe"
        Espresso.onView(ViewMatchers.withId(binding.setUserName.id))
            .perform(ViewActions.typeText(testUserName), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(binding.setUserName.id))
            .check(ViewAssertions.matches(ViewMatchers.withText(testUserName)))
    }

    @Test
    fun testBioEditTextContent() {
        
        // Click the Update button
        Espresso.onView(ViewMatchers.withId(binding.updateSettingsBtn.id))
            .perform(ViewActions.click())
        Thread.sleep(1000)
        val testBio = "I love coding!"
        Espresso.onView(ViewMatchers.withId(binding.setBio.id))
            .perform(ViewActions.typeText(testBio), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(binding.setBio.id))
            .check(ViewAssertions.matches(ViewMatchers.withText(testBio)))
    }

    @Test
    fun testUpdateButtonClicked() {
        val testUserName = "John Doe"
        val testBio = "I love coding!"
        
        // Click the Update button
        Espresso.onView(ViewMatchers.withId(binding.updateSettingsBtn.id))
            .perform(ViewActions.click())
        Thread.sleep(1000)
        // Verify that the EditText fields are editable
        Espresso.onView(ViewMatchers.withId(binding.setUserName.id))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
        Espresso.onView(ViewMatchers.withId(binding.setBio.id))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
        // Enter values in EditText fields
        Espresso.onView(ViewMatchers.withId(binding.setUserName.id))
            .perform(ViewActions.typeText(testUserName), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(binding.setBio.id))
            .perform(ViewActions.typeText(testBio), ViewActions.closeSoftKeyboard())
        // Verify that the values are saved
        Espresso.onView(ViewMatchers.withId(binding.setUserName.id))
            .check(ViewAssertions.matches(ViewMatchers.withText(testUserName)))
        Espresso.onView(ViewMatchers.withId(binding.setBio.id))
            .check(ViewAssertions.matches(ViewMatchers.withText(testBio)))
    }

    @Test
    fun testEditModeToggle() {
//        
        // Enter edit mode
        Espresso.onView(ViewMatchers.withId(binding.updateSettingsBtn.id))
            .perform(ViewActions.click())
         Thread.sleep(1000)
        // Verify that EditText fields are enabled
        Espresso.onView(ViewMatchers.withId(binding.setUserName.id))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
        Espresso.onView(ViewMatchers.withId(binding.setBio.id))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))

        // Exit edit mode
        Espresso.onView(ViewMatchers.withId(binding.updateSettingsBtn.id))
            .perform(ViewActions.click())

        // Verify that EditText fields are disabled
        Espresso.onView(ViewMatchers.withId(binding.setUserName.id))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
        Espresso.onView(ViewMatchers.withId(binding.setBio.id))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
    }

    @Test
    fun testSaveButtonClicked() {
        
        // Enter edit mode
        Espresso.onView(ViewMatchers.withId(binding.updateSettingsBtn.id))
            .perform(ViewActions.click())

        // Click the Save button
        Espresso.onView(ViewMatchers.withId(binding.updateSettingsBtn.id))
            .perform(ViewActions.click())
        Thread.sleep(1000)
        // Verify that the button text changes to "Update"
        Espresso.onView(ViewMatchers.withId(binding.updateSettingsBtn.id))
            .check(ViewAssertions.matches(ViewMatchers.withText("Edit Profile")))
    }

    @Test
    fun testUpdateButtonDisplayedOnStartup() {
        // Verify that the button text is "Update" on startup
        Espresso.onView(ViewMatchers.withId(binding.updateSettingsBtn.id))
            .check(ViewAssertions.matches(ViewMatchers.withText("Edit Profile")))
    }
}