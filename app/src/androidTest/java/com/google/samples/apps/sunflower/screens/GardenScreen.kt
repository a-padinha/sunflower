/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.utilities.TestHelpers


class GardenScreen {

    private val testHelpers = TestHelpers()

    //Ids
    private val emptyGarden: Int = R.id.empty_garden
    private val tabs: Int = R.id.tabs
    private val plantName: Int = R.id.plant_name
    private val plantedDate: Int = R.id.plant_date
    private val wateredDate: Int = R.id.water_date
    private val waterInterval: Int = R.id.water_interval

    //Steps
    fun gardenIsEmptyIsDisplayed() {
        onView(withId(emptyGarden))
            .check(matches(isDisplayed()))
    }

    fun tapOnGardenTab() {
        onView(withId(tabs))
            .perform(testHelpers.selectTabAtPosition(0))
    }

    fun plantNameIsDisplayed() {
        onView(withId(plantName))
            .check(matches(isDisplayed()))
    }

    fun plantPlantedDateIsDisplayed() {
        onView(withId(plantedDate))
            .check(matches(isDisplayed()))
    }

    fun plantLastWateredIsDisplayed() {
        onView(withId(wateredDate))
            .check(matches(isDisplayed()))
    }

    fun plantWaterIntervalIsDisplayed() {
        onView(withId(waterInterval))
            .check(matches(isDisplayed()))
    }

    fun tapOnPlantDetail() {
        onView(withId(plantName))
            .perform(click())
    }
}
