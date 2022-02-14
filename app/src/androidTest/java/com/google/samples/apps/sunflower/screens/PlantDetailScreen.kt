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

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.samples.apps.sunflower.R
import org.hamcrest.CoreMatchers

class PlantDetailScreen {

    //Ids
    private val detailImage: Int = R.id.detail_image
    private val plantDescription: Int = R.id.plant_description
    private val addPlant: Int = R.id.fab
    private val share: Int = R.id.action_share

    //Steps
    fun plantDetailImageIsDisplayed() {
        onView(withId(detailImage))
            .check(matches(isDisplayed()))
    }

    fun plantDetailDescriptionIsDisplayed() {
        onView(withId(plantDescription))
            .check(matches(isDisplayed()))
    }

    fun tapOnAddPlant() {
        onView(withId(addPlant))
            .perform(click())
    }

    fun addPlantIsNotDisplayed() {
        onView(withId(addPlant))
            .check(matches(CoreMatchers.not(isDisplayed())))
    }

    fun tapOnShare() {
        onView(withId(share))
            .check(matches(isDisplayed()))
            .perform(click())
    }

    fun pressBack() {
        Espresso.pressBack()
    }
}