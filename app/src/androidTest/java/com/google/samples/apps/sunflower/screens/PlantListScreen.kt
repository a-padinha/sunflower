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

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.utilities.TestHelpers

class PlantListScreen {

    private val testHelpers = TestHelpers()

    //Ids
    private val tabs: Int = R.id.tabs
    private val plantList: Int = R.id.plant_list

    //Steps
    fun tapOnPlantListTab() {
        onView(withId(tabs))
            .perform(testHelpers.selectTabAtPosition(1))
    }

    fun scrollAndTapOnSunflower() {
        onView(withId(plantList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    13,
                    click()
                )
            )
    }
}