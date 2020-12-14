package com.benjamin.dugas.maru;

import android.content.Context;
import android.widget.EditText;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.benjamin.dugas.maru.DI.DI;
import com.benjamin.dugas.maru.model.Meeting;
import com.benjamin.dugas.maru.service.MeetingApiService;
import com.benjamin.dugas.maru.ui.meeting_list.ListMeetingActivity;
import com.benjamin.dugas.maru.utils.DeleteViewAction;
import com.google.android.material.textfield.TextInputEditText;

import junit.extensions.ActiveTestSuite;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.benjamin.dugas.maru.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

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
        onView(ViewMatchers.withId(R.id.fab_add)).perform(click());
        onView(ViewMatchers.withId(R.id.add)).check(matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void meetingShouldShowOnListMeeting() {
        int count = service.getMeeting().size();
        onView(ViewMatchers.withId(R.id.meeting_list)).check(withItemCount(count));
        onView(ViewMatchers.withId(R.id.fab_add)).perform(click());
        onView(ViewMatchers.withId(R.id.tiet_topic)).perform(typeText("Test"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.rb_f)).perform(click());
        onView(ViewMatchers.withId(R.id.rb_10)).perform(click());
        onView(ViewMatchers.withId(R.id.tiet_participant)).perform(typeText("Test1"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.b_add)).perform(click());
        onView(ViewMatchers.withId(R.id.tiet_participant)).perform(typeText("Test2"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.b_create)).perform(click());
        onView(ViewMatchers.withId(R.id.meeting_list)).check(withItemCount(count+1));
    }

    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem() {
        int count = service.getMeeting().size();
        onView(ViewMatchers.withId(R.id.meeting_list)).check(withItemCount(count));
        onView(ViewMatchers.withId(R.id.fab_add)).perform(click());
        onView(ViewMatchers.withId(R.id.tiet_topic)).perform(typeText("Test"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.rb_f)).perform(click());
        onView(ViewMatchers.withId(R.id.rb_10)).perform(click());
        onView(ViewMatchers.withId(R.id.tiet_participant)).perform(typeText("Test1"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.b_add)).perform(click());
        onView(ViewMatchers.withId(R.id.tiet_participant)).perform(typeText("Test2"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.b_create)).perform(click());
        onView(ViewMatchers.withId(R.id.meeting_list)).check(withItemCount(count+1));
        onView(ViewMatchers.withId(R.id.meeting_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        onView(ViewMatchers.withId(R.id.meeting_list)).check(withItemCount(count));
    }

    @Test
    public void myMeetingList_filterAction_shouldShowFilterItem() {
        int count = service.getMeeting().size();
        onView(ViewMatchers.withId(R.id.meeting_list)).check(withItemCount(count));
        onView(ViewMatchers.withId(R.id.fab_add)).perform(click());
        onView(ViewMatchers.withId(R.id.tiet_topic)).perform(typeText("Test"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.rb_f)).perform(click());
        onView(ViewMatchers.withId(R.id.rb_10)).perform(click());
        onView(ViewMatchers.withId(R.id.tiet_participant)).perform(typeText("Test1"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.b_add)).perform(click());
        onView(ViewMatchers.withId(R.id.tiet_participant)).perform(typeText("Test2"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.b_create)).perform(click());
        onView(ViewMatchers.withId(R.id.meeting_list)).check(withItemCount(count+1));
        onView(ViewMatchers.withId(R.id.fab_add)).perform(click());
        onView(ViewMatchers.withId(R.id.tiet_topic)).perform(typeText("Test1"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.rb_f)).perform(click());
        onView(ViewMatchers.withId(R.id.rb_10)).perform(click());
        onView(ViewMatchers.withId(R.id.tiet_participant)).perform(typeText("Test3"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.b_add)).perform(click());
        onView(ViewMatchers.withId(R.id.tiet_participant)).perform(typeText("Test4"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.b_create)).perform(click());
        onView(ViewMatchers.withId(R.id.meeting_list)).check(withItemCount(count+2));
        onView(ViewMatchers.withId(R.id.fab_add)).perform(click());
        onView(ViewMatchers.withId(R.id.tiet_topic)).perform(typeText("Test2"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.rb_a)).perform(click());
        onView(ViewMatchers.withId(R.id.rb_13)).perform(click());
        onView(ViewMatchers.withId(R.id.tiet_participant)).perform(typeText("Test5"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.b_add)).perform(click());
        onView(ViewMatchers.withId(R.id.tiet_participant)).perform(typeText("Test6"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.b_create)).perform(click());
        onView(ViewMatchers.withId(R.id.meeting_list)).check(withItemCount(count+3));
        onView(ViewMatchers.withId(R.id.action_settings)).perform(click());
        onView(ViewMatchers.withId(R.id.menuItem_hour)).perform(click());
        onView(ViewMatchers.withId(R.id.i_10h00)).perform(click());
        onView(ViewMatchers.withId(R.id.meeting_list)).check(withItemCount(count+1));
        onView(ViewMatchers.withId(R.id.action_settings)).perform(click());
        onView(ViewMatchers.withId(R.id.menuItem_location)).perform(click());
        onView(ViewMatchers.withId(R.id.i_a)).perform(click());
        onView(ViewMatchers.withId(R.id.meeting_list)).check(withItemCount(count+2));
    }
}