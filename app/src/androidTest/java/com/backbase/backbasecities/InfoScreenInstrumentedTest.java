package com.backbase.backbasecities;

import android.content.Intent;
import android.widget.EditText;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InfoScreenInstrumentedTest {

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void setUp() {
        activityRule.launchActivity(new Intent());
    }

    @Test
    public void testSearchSuggestionDisplayed() throws InterruptedException {

        String CITY_STRING = "Alabama";
        String COUNTRY_STRING = ", US";

        onView(isAssignableFrom(EditText.class)).perform(typeText(CITY_STRING), click());

        Thread.sleep(2000);

        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        onView(withId(R.id.about_info)).perform(click());

        Thread.sleep(2000);

        onView(withId(R.id.info_name_textView)).check(matches(withText(CITY_STRING+COUNTRY_STRING)));

        onView(withId(R.id.close_button)).perform(click());

    }

}
