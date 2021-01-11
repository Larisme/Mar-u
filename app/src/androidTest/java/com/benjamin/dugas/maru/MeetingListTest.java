package com.benjamin.dugas.maru;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.benjamin.dugas.maru.DI.DI;
import com.benjamin.dugas.maru.service.MeetingApiService;
import com.benjamin.dugas.maru.ui.meeting_list.ListMeetingActivity;
import com.benjamin.dugas.maru.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.benjamin.dugas.maru.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingListTest {

    private ListMeetingActivity mActivity;
    private MeetingApiService service;

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule =
            new ActivityTestRule(ListMeetingActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void clickOnActionBouton_shouldShowAddMeeting() {
        onView(withId(R.id.fab_add)).perform(click());
        onView(withId(R.id.add)).check(matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem() {
        int count = service.getMeeting().size();
        onView(withId(R.id.meeting_list)).check(withItemCount(count));
        onView(withId(R.id.meeting_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        onView(withId(R.id.meeting_list)).check(withItemCount(count-1));
    }

    @Test
    public void myMeetingList_filterAction_shouldShowFilterItem() {
        int count = service.getMeeting().size();
        onView(withId(R.id.meeting_list)).check(withItemCount(count));
        onView(withId(R.id.action_settings)).perform(click());
        onView(ViewMatchers.withText("Hour")).perform(click());
        onView(ViewMatchers.withText("10h00")).perform(click());
        onView(withId(R.id.meeting_list)).check(withItemCount(count-1));
        onView(withId(R.id.action_settings)).perform(click());
        onView(ViewMatchers.withText("Location")).perform(click());
        onView(ViewMatchers.withText("A")).perform(click());
        onView(withId(R.id.meeting_list)).check(withItemCount(count-2));
    }
}