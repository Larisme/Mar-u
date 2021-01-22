package com.benjamin.dugas.maru;

import android.widget.DatePicker;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.benjamin.dugas.maru.DI.DI;
import com.benjamin.dugas.maru.service.MeetingApiService;
import com.benjamin.dugas.maru.ui.meeting_list.ListMeetingActivity;
import com.benjamin.dugas.maru.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
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
    public void myMeetingList_filterAction_shouldShowFilterItem() {
        int count = service.getMeeting().size();
        onView(withId(R.id.meeting_list)).check(withItemCount(count));
        onView(withId(R.id.action_settings)).perform(click());
        onView(ViewMatchers.withText("Date")).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021, 3, 10));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.meeting_list)).check(withItemCount(count-1));
        onView(withId(R.id.action_settings)).perform(click());
        onView(ViewMatchers.withText("Location")).perform(click());
        onView(ViewMatchers.withText("A")).perform(click());
        onView(withId(R.id.meeting_list)).check(withItemCount(count-2));
    }

    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem() {
        int count = service.getMeeting().size();
        onView(withId(R.id.meeting_list)).check(withItemCount(count));
        onView(withId(R.id.meeting_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        onView(withId(R.id.meeting_list)).check(withItemCount(count-1));
    }

    @Test
    public void clickOnActionBouton_shouldShowAddMeeting() {
        int count = service.getMeeting().size();
        onView(withId(R.id.meeting_list)).check(withItemCount(count));
        onView(withId(R.id.fab_add)).perform(click());
        onView(withId(R.id.add)).check(matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.tiet_topic)).perform(typeText("Test")).perform(closeSoftKeyboard());
        onView(withId(R.id.rb_a)).perform(click());
        onView(withId(R.id.tiet_participant)).perform(scrollTo());
        onView(withId(R.id.tiet_participant)).perform(typeText("Sacha@lamzone.com"));
        onView(withId(R.id.b_add)).perform(click());
        onView(withId(R.id.tiet_participant)).perform(typeText("Pierre@lamzone.com")).perform(closeSoftKeyboard());
        onView(withId(R.id.add)).perform(scrollTo());
        onView(withId(R.id.b_create)).perform(scrollTo()).perform(click());
        onView(withId(R.id.meeting_list)).check(withItemCount(count+1));
    }
}