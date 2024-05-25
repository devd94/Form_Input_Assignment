package com.devina.ekacareformassignment

import android.widget.DatePicker
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions.setDate
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.devina.ekacareformassignment.ui.form.FormActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FormActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(FormActivity::class.java)

    @Test
    fun testSaveButtonSuccess()
    {
        //type name
        onView(withId(R.id.et_name_input)).perform(typeText("heera"))
        //type age
        onView(withId(R.id.et_age_input)).perform(typeText("29"))
        //close keyboard
        closeSoftKeyboard()
        //select dob
        testDatePicker()
        //click save button
        onView(withId(R.id.btn_save)).perform(click())
        //check success by checking snackbar message
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(R.string.user_saved_msg)))
    }

    @Test
    fun testDatePicker()
    {
        // Show the date picker
        onView(withId(R.id.tv_dob_input)).perform(click())

        //sets time on date picker
        onView(isAssignableFrom(DatePicker::class.java)).perform(setDate(1994, 8, 24))

        //close the date picker by selecting positive ok button
        onView(withId(android.R.id.button1)).perform(click())

        //check the date displayed on textview
        onView(withId(R.id.tv_dob_input)).check(matches(allOf(withText("24-8-1994"))))
    }

    @Test
    fun testEmptyNameOnBtnSaveClick()
    {
        //click save button
        onView(withId(R.id.btn_save)).perform(click())
        //check success by checking snackbar message
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(R.string.name_reqd_error_msg)))
    }

    @Test
    fun testEmptyAgeOnBtnSaveClick()
    {
        //type name
        onView(withId(R.id.et_name_input)).perform(typeText("heera"))
        //close keyboard
        closeSoftKeyboard()
        //select dob
        testDatePicker()
        //click save button
        onView(withId(R.id.btn_save)).perform(click())
        //check success by checking snackbar message
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(R.string.age_reqd_error_msg)))
    }

    @Test
    fun testEmptyDobOnBtnSaveClick()
    {
        //type age
        onView(withId(R.id.et_age_input)).perform(typeText("29"))
        //type name
        onView(withId(R.id.et_name_input)).perform(typeText("heera"))
        //close keyboard
        closeSoftKeyboard()
        //click save button
        onView(withId(R.id.btn_save)).perform(click())
        //check success by checking snackbar message
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(R.string.dob_reqd_error_msg)))
    }

    @Test
    fun testAgeDobMismatchOnSaveClick()
    {
        //type name
        onView(withId(R.id.et_name_input)).perform(typeText("heera"))
        //type age
        onView(withId(R.id.et_age_input)).perform(typeText("22"))
        //close keyboard
        closeSoftKeyboard()
        //select dob
        testDatePicker()
        //click save button
        onView(withId(R.id.btn_save)).perform(click())
        //check success by checking snackbar message
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(R.string.age_dob_mismatch_error_msg)))
    }
}