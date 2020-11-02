package com.benjamin.dugas.maru.ui.meeting_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.benjamin.dugas.maru.DI.DI;
import com.benjamin.dugas.maru.R;
import com.benjamin.dugas.maru.service.MeetingApiService;

import butterknife.OnClick;

public class ListMeetingActivity extends AppCompatActivity {

    private MeetingApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mApiService = DI.getMeetingApiService();
        setSupportActionBar(toolbar);

        if (findViewById(R.id.fl_container) != null) {
            if(savedInstanceState != null)
                return;
            getSupportFragmentManager().beginTransaction().add(R.id.fl_container, new MeetingFragment()).commit();
        }

    }

    @OnClick(R.id.fab_add)
    void addMeeting() {
        Intent addMeetingActivityIntent = new Intent(this, AddMeetingActivity.class);
        startActivity(addMeetingActivityIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.i_09h00:
                mApiService.filterMeetingByHour("09h00");
                return true;
            case R.id.i_10h00:
                mApiService.filterMeetingByHour("10h00");
                return true;
            case R.id.i_11h00:
                mApiService.filterMeetingByHour("11h00");
                return true;
            case R.id.i_12h00:
                mApiService.filterMeetingByHour("12h00");
                return true;
            case R.id.i_13h00:
                mApiService.filterMeetingByHour("13h00");
                return true;
            case R.id.i_14h00:
                mApiService.filterMeetingByHour("14h00");
                return true;
            case R.id.i_15h00:
                mApiService.filterMeetingByHour("15h00");
                return true;
            case R.id.i_16h00:
                mApiService.filterMeetingByHour("16h00");
                return true;
            case R.id.i_17h00:
                mApiService.filterMeetingByHour("17h00");
                return true;
            case R.id.i_18h00:
                mApiService.filterMeetingByHour("18h00");
                return true;
            case R.id.i_19h00:
                mApiService.filterMeetingByHour("19h00");
                return true;
            case R.id.i_a:
                mApiService.filterMeetingByHour("A");
                return true;
            case R.id.i_b:
                mApiService.filterMeetingByHour("B");
                return true;
            case R.id.i_c:
                mApiService.filterMeetingByHour("C");
                return true;
            case R.id.i_d:
                mApiService.filterMeetingByHour("D");
                return true;
            case R.id.i_e:
                mApiService.filterMeetingByHour("E");
                return true;
            case R.id.i_f:
                mApiService.filterMeetingByHour("F");
                return true;
            case R.id.i_g:
                mApiService.filterMeetingByHour("G");
                return true;
            case R.id.i_h:
                mApiService.filterMeetingByHour("H");
                return true;
            case R.id.i_i:
                mApiService.filterMeetingByHour("I");
                return true;
            case R.id.i_j:
                mApiService.filterMeetingByHour("J");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}