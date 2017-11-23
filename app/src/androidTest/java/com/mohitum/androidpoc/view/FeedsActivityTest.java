package com.mohitum.androidpoc.view;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.mohitum.androidpoc.R;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class FeedsActivityTest{

    ActivityTestRule<FeedsActivity> activityTestRule = new ActivityTestRule<>(FeedsActivity.class);

    /**
     * UI Test that asserts the state of the screen when set up with all data present
     */
    @Test
    public void checkAllFeedsLoaded() {
        activityTestRule.launchActivity(new Intent());
        RecyclerView recyclerView = (RecyclerView) activityTestRule.getActivity().findViewById(R.id.rec_vw_feeds);
        int itemsCount = recyclerView.getAdapter().getItemCount();
        Espresso.onView(ViewMatchers.withId(R.id.rec_vw_feeds)).check(matches(isDisplayed()));
        assertEquals(itemsCount, activityTestRule.getActivity().getDataSize());
    }

    /**
     * UI Test that asserts the state of the screen when in an error state no wifi/data
     * assumption: wifi/ mobile data is disabled
     */
    @Test
    public void checkErrorMessageIsDisplayedForNoInternet() {
        //TODO: Mock network
        activityTestRule.launchActivity(new Intent());
        Espresso.onView(ViewMatchers.withId(R.id.txt_vw_error)).check(matches(isDisplayed()));
    }

}
