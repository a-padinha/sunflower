[![GitHub issues](https://img.shields.io/github/issues/a-padinha/sunflower?style=plastic)](https://github.com/a-padinha/sunflower/issues)
[![GitHub forks](https://img.shields.io/github/forks/a-padinha/sunflower)](https://github.com/a-padinha/sunflower/network)
[![GitHub stars](https://img.shields.io/github/stars/a-padinha/sunflower)](https://github.com/a-padinha/sunflower/stargazers)

## Espresso UI Tests Exercise
This is a small test suite with Espresso tests task using the `Android/sunflower` app project.

## Table of contents
* [Description](#description)
* [Challenges](#Challenges)
* [Setup](#setup)

## Description

<details>
<summary>
This is the Test scenario:
</summary>
<pre>
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
</pre>
</details>

<details>
<summary>
This is the test automated result:
</summary>
<pre>
   @Test
    gardenAndPlantListSanity() {
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
</pre>
</details>

## Breaking down the manual test scenario into smaller/single responsability tests

The scenario provided does lots of things, it navigates through 3 different screens and these screens have different states as well.
Writing the test exactly as it was provided created a large test difficult to debug if some step fails or asyncronous 
operations affect the test, the test itself basically does a sanity run so for a production product I would devide it in 4 tests:
`openSunflowerPlantDetail()`, `addSunflowerToGarden()`, `addSunflowerOnceOnly()` and `shareSunflower()` I wasn't able do this change now
because of the challenges I encountered in terms of test execution and test clean up after runs, see section challenges for more details.
The smaller tests with a single responsability would be beneficial to reuse in other tests.
The test also is specific to a sunflower, I didn't assert anywhere on the test the flower being added to the Garden was in fact a plant 
with a plant_name matching the string "Sunflower", UI tests shouldn't check for text, but also I had a look a the unit test and they are 
asserting that the content displays in the right places/order meaning that part is covered rightfully in the right testing layer.

## Launching the app and exploring it's testability

It was quite easy to get reliable identifiers as there were ids in the project to almost all the interactions 
and checks necessary. The other routes would be to look for strings AND/OR positions in the layout which isn't great but doesn't 
touch the app code, another way and more consistent one would be to add the ids on the main project changing slightly the app code
where needed.

## Files added/changed

The project already had Unit tests and 2 UI tests (one was using a tag to be ignored) so I followed the structure that was 
being used and tweaked the architecture to use the PageObjects pattern for the test added, this meant creating a 
"screens" package containing the classes with the functions associated with each screen. I added a helpers class to the utilities
package for helper functions and left the test class where the other test classes were under com.google.samples.app.sunflower.
The project uses Rules with Hilt to manage how the tests run.

Files highlighted in Blue are the addictions under the `origin/A-1_addUITestsWithEspresso` branch.

<img width="314" alt="Screenshot 2022-02-15 at 09 11 36" src="https://user-images.githubusercontent.com/10550674/154031781-ee42dca1-b329-41e4-a346-776a99b22c78.png">

Libraries used: AndroidX, Espresso and Hamcrest.
Language: Kotlin


## Challenges :nut_and_bolt:

I encountered a couple issues...
1) Running the tests via gradle and using the IDE sometimes fails with the following exception: 
```
java.lang.IllegalStateException: WorkManager is not initialized properly. You have explicitly disabled WorkManagerInitializer in your manifest, have not manually called WorkManager#initialize at this point, and your Application does not implement Configuration.Provider.
```
To fix this I would firstly create an issue at the android/sunflower repo in order to expose this evidence and get some feedback.
I would also try a couple things I found about the subject while investigating related with editing the custom WorkManager 
initialize. To work around this issue for now I invalidated caches (File > Invalidate caches), and did Clean/Rebuild project 
(Build > Clean Project Build > Build Project) this would clear the warning for some time. 

2) There is deprecation related with Rules, the `androidx.test.rule.ActivityTestRule`. This means that Rules aren't working properly probably affecting 
the way the test setup and teardown is set to clean up after the tests run and so when I run a test at the moment locally the app installed still has 
the database changes the test did which affects the next run. To work around this and focus on the task which was to build a test, I used the emulator
settings to clean caches and storage in the sunflower app manually after the test runs.
In a product team context this isn't ideal at all for a production feature, its just a start to get something working and running and explore the challenges 
after with the team that has the project context already.

I believe that fixing this deprecation would solve part of the problem, another way to understand the context of the this issue would be to get in touch 
with some of the Android/sunflower contributers reporting an issue to start the conversation on how to fix this with more investigation.

## Things I would do next :green_book:

- Fix the buiding and test executing issues and ensure tests run indenpendently.
- Connect this repo to a free CI account and a test lab.
- Experiment with other test design patterns like Robots Pattern and Screenplay Pattern in order to see which one would benefit the project in the long run.
- Explore ways of writing readable steps with Gherkin and see pros and cons according to the project needs.
- Explore test Rules and Hilt in terms of test setups and teardowns, data management with the goal for creating independent and fast tests.
- Create annotations associated with app areas so I can run specific tests on a single app section via a gradle commmand, as the project grows this would make
the testing CI step faster and focused on the area that needs feedback on development leaving potentially the full runs to overnight schedules.
- Explore test reporting adding more logs and screenshots with `androidx.test.runner.screenshot` for example.
- Look at the possibilities to run a device farm with cloud devices to tackle android fragmentation - increasing the devices coverage.


## Setup 

- To set up the project use the instructions [here](https://github.com/android/sunflower#getting-started).
- The project is set by default to run the tests with gradle using the command
```
$ ./gradlew connectedAndroidTest   
```
But! if you run into build/run problems like I did, one solution in order to see the test running is to run the test from the IDE.
1. Checkout the branch `origin/A-1_addUITestsWithEspresso`
2. Open Android studio 
3. Run the test from the class


