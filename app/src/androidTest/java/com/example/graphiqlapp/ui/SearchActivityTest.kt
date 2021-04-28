package com.example.graphiqlapp.ui

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.graphiqlapp.R
import com.example.graphiqlapp.ui.search.SearchActivity
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SearchActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<SearchActivity>()

    @Before
    fun setUp() {
        val scenario = launchActivity<SearchActivity>()
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.moveToState(Lifecycle.State.STARTED)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun when_TheActivityStart_ProgressBar_Displayed() {

        assertDisplayed(R.id.progress)
    }

    @Test
    fun when_TheActivityStart_SearchBar_should_be_empty() {

        assertDisplayed(R.id.input, "")
    }

    @Test
    fun when_TheActivityStart_SearchBar_shoulempty() {

        clickOn(R.id.button_search)
        assertDisplayed(R.id.progress)
    }
}