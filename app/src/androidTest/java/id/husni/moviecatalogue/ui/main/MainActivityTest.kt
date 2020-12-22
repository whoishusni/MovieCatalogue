package id.husni.moviecatalogue.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import id.husni.moviecatalogue.R
import id.husni.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityTest = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdling())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdling())
    }

    @Test
    fun loadMoviesAndDetail(){
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
    }

    @Test
    fun loadSeriesAndDetail(){
        onView(withId(R.id.mainViewPager)).perform(swipeLeft())
        onView(withId(R.id.rvSeries)).check(matches(isDisplayed()))
        onView(withId(R.id.rvSeries)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
    }

    @Test
    fun addMoviesToFavourite(){
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(R.id.action_like)).check(matches(isDisplayed()))
        onView(withId(R.id.action_like)).perform(click())
    }

    @Test
    fun deleteMoviesFromFavourite(){
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(R.id.action_like)).perform(click())
        onView(withId(R.id.action_like)).perform(click())
    }

    @Test
    fun addSeriesToFavourite(){
        onView(withId(R.id.mainViewPager)).perform(swipeLeft())
        onView(withId(R.id.rvSeries)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(R.id.action_like)).check(matches(isDisplayed()))
        onView(withId(R.id.action_like)).perform(click())
    }

    @Test
    fun deleteSeriesFromFavourite(){
        onView(withId(R.id.mainViewPager)).perform(swipeLeft())
        onView(withId(R.id.rvSeries)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(R.id.action_like)).perform(click())
        onView(withId(R.id.action_like)).perform(click())
    }

    @Test
    fun loadFavouriteMoviesAndDetail(){
        onView(withId(R.id.action_to_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.action_to_fav)).perform(click())
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))

    }

}