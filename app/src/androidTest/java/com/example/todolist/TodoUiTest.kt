package com.example.todolist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class TodoUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private fun waitForText(text: String) {
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(text).fetchSemanticsNodes().isNotEmpty()
        }
    }

    @Test
    fun list_displaysAllThreeTodos() {
        waitForText("Купить молоко")

        composeTestRule.onNodeWithText("Купить молоко").assertIsDisplayed()
        composeTestRule.onNodeWithText("Позвонить маме").assertIsDisplayed()
        composeTestRule.onNodeWithText("Сделать ДЗ по Android").assertIsDisplayed()
    }

    @Test
    fun checkbox_togglesStatus() {
        waitForText("Купить молоко")

        composeTestRule.onNodeWithTag("checkbox_1", useUnmergedTree = true).assertIsOff()
        composeTestRule.onNodeWithTag("checkbox_1", useUnmergedTree = true).performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("checkbox_1", useUnmergedTree = true).assertIsOn()
    }

    @Test
    fun navigation_listToDetailAndBack() {
        waitForText("Купить молоко")

        composeTestRule.onNodeWithText("Купить молоко").performClick()
        waitForText("Не выполнено")
        composeTestRule.onNodeWithText("Не выполнено").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Назад").performClick()
        waitForText("Позвонить маме")
        composeTestRule.onNodeWithText("Позвонить маме").assertIsDisplayed()
    }
}