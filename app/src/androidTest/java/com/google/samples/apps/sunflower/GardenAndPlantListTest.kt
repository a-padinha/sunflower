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


package com.google.samples.apps.sunflower

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.google.samples.apps.sunflower.screens.GardenScreen
import com.google.samples.apps.sunflower.screens.PlantDetailScreen
import com.google.samples.apps.sunflower.screens.PlantListScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

@LargeTest
@HiltAndroidTest
class GardenAndPlantListTest {

    private val hiltRule = HiltAndroidRule(this)
    private val activityTestRule = ActivityTestRule(GardenActivity::class.java)

    private val gardenScreen = GardenScreen()
    private val plantListScreen = PlantListScreen()
    private val plantDetailScreen = PlantDetailScreen()

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)

    /*
     * Scenario: Garden and Plant List Sanity
     *
     * 1. Open app
     * 2. Check that Garden is empty
     * 3. Open Plant list
     * 4. Scroll to sunflower
     * 5. Open it
     * 6. Check we have img and description and can add plant to garden
     * 7. Add it to garden
     * 8. Check that user can't add more than one ('Add' button disappeared)
     * 9. Open My Garden
     * 10. Check Sunflower was added to garden and have planted date/last watered date and should be
     *     watered in 3 days from last watered date
     * 11. Open it
     * 12. Check we have img and description and don't have 'add plant to garden' button
     * 13. Check that user can share link to this plant via share icon from right corner
     */

    @Test
    fun gardenAndPlantListSanity() {
        gardenScreen.gardenIsEmptyIsDisplayed()
        plantListScreen.tapOnPlantListTab()
        plantListScreen.scrollAndTapOnSunflower()
        plantDetailScreen.plantDetailImageIsDisplayed()
        plantDetailScreen.plantDetailDescriptionIsDisplayed()
        plantDetailScreen.tapOnAddPlant()
        plantDetailScreen.addPlantIsNotDisplayed()
        plantDetailScreen.pressBack()
        gardenScreen.tapOnGardenTab()
        gardenScreen.plantNameIsDisplayed()
        gardenScreen.plantPlantedDateIsDisplayed()
        gardenScreen.plantLastWateredIsDisplayed()
        gardenScreen.plantWaterIntervalIsDisplayed()
        gardenScreen.tapOnPlantDetail()
        plantDetailScreen.plantDetailImageIsDisplayed()
        plantDetailScreen.plantDetailDescriptionIsDisplayed()
        plantDetailScreen.addPlantIsNotDisplayed()
        plantDetailScreen.tapOnShare()
    }
}
