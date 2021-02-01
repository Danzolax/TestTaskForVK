package com.zolax.appforvk

import android.content.pm.ActivityInfo
import androidx.core.content.MimeTypeFilter.matches
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private val TEST_STRING = "test@$#$(*test214"
    private val BUTTON_TEXT = "Button"


    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)


    //Проверка того, что поле для ввода видно и оно пустое, а также в него можно вписывать текст
    @Test
    fun testEmptyInputField(){
        Espresso.onView(ViewMatchers.withId(R.id.inputText))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
            .check(ViewAssertions.matches(ViewMatchers.isFocusable()))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))
            .check(ViewAssertions.matches(withText("")))
    }

    //Проверка того, что поле для вывода видно и оно пустое
    @Test
    fun testEmptyOutputField(){
        Espresso.onView(ViewMatchers.withId(R.id.inputText))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
            .check(ViewAssertions.matches(withText("")))
    }

    //Проверка того, что кнопку видно, она нажимается и на ней отображено верное название
    @Test
    fun testButton(){
        Espresso.onView(ViewMatchers.withId(R.id.sendTextBtn))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
            .check(ViewAssertions.matches(ViewMatchers.isFocusable()))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))
            .check(ViewAssertions.matches(withText(BUTTON_TEXT)))
    }

    //Проверка того, что в поле ввода, вводится текст
    @Test
    fun testEnterTextInInputTextField() {
        Espresso
            .onView(ViewMatchers.withId(R.id.inputText))
            .perform(ViewActions.typeText(TEST_STRING))
            .check(ViewAssertions.matches(withText(TEST_STRING)))
    }

    //Проверка того, что вводимый текст, совпадает с текстом помещенным в лейбл после нажатия на кнопку
    @Test
    fun testValidOutputText() {
        Espresso
            .onView(ViewMatchers.withId(R.id.inputText))
            .perform(ViewActions.typeText(TEST_STRING))
        Espresso
            .onView(ViewMatchers.withId(R.id.sendTextBtn))
            .perform(ViewActions.click())
        Espresso
            .onView(ViewMatchers.withId(R.id.outputText))
            .check(ViewAssertions.matches(withText(TEST_STRING)))
    }

    //Проверка того, что после поворота экрана текст не пропал из лейблы
    @Test
    fun testUIStateAfterRotate(){
        Espresso
            .onView(ViewMatchers.withId(R.id.inputText))
            .perform(ViewActions.typeText(TEST_STRING))
        Espresso
            .onView(ViewMatchers.withId(R.id.sendTextBtn))
            .perform(ViewActions.click())
        activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        Espresso
            .onView(ViewMatchers.withId(R.id.outputText))
            .check(ViewAssertions.matches(withText(TEST_STRING)))
    }

}