package com.benjamin.dugas.maru.ui.meeting_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.benjamin.dugas.maru.DI.DI;
import com.benjamin.dugas.maru.R;
import com.benjamin.dugas.maru.service.MeetingApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
            getSupportFragmentManager().beginTransaction().add(R.id.fl_container, new MeetingFragment(),"meeting").commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddButtonClicked();
            }
        });
    }

    private void mAddButtonClicked() { startActivity(new Intent(this, AddMeetingActivity.class)); }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MeetingFragment fragment = (MeetingFragment)getSupportFragmentManager().findFragmentByTag("meeting");
        if (fragment == null)
            return true;
        switch (item.getItemId()) {
            case R.id.i_09h00:
                fragment.initListFilter(mApiService.filterMeetingByHour(9));
                return true;
            case R.id.i_10h00:
                fragment.initListFilter(mApiService.filterMeetingByHour(10));
                return true;
            case R.id.i_11h00:
                fragment.initListFilter(mApiService.filterMeetingByHour(11));
                return true;
            case R.id.i_12h00:
                fragment.initListFilter(mApiService.filterMeetingByHour(12));
                return true;
            case R.id.i_13h00:
                fragment.initListFilter(mApiService.filterMeetingByHour(13));
                return true;
            case R.id.i_14h00:
                fragment.initListFilter(mApiService.filterMeetingByHour(14));
                return true;
            case R.id.i_15h00:
                fragment.initListFilter(mApiService.filterMeetingByHour(15));
                return true;
            case R.id.i_16h00:
                fragment.initListFilter(mApiService.filterMeetingByHour(16));
                return true;
            case R.id.i_17h00:
                fragment.initListFilter(mApiService.filterMeetingByHour(17));
                return true;
            case R.id.i_18h00:
                fragment.initListFilter(mApiService.filterMeetingByHour(18));
                return true;
            case R.id.i_19h00:
                fragment.initListFilter(mApiService.filterMeetingByHour(19));
                return true;
            case R.id.i_a:
                fragment.initListFilter(mApiService.filterMeetingByLocation("A"));
                return true;
            case R.id.i_b:
                fragment.initListFilter(mApiService.filterMeetingByLocation("B"));
                return true;
            case R.id.i_c:
                fragment.initListFilter(mApiService.filterMeetingByLocation("C"));
                return true;
            case R.id.i_d:
                fragment.initListFilter(mApiService.filterMeetingByLocation("D"));
                return true;
            case R.id.i_e:
                fragment.initListFilter(mApiService.filterMeetingByLocation("E"));
                return true;
            case R.id.i_f:
                fragment.initListFilter(mApiService.filterMeetingByLocation("F"));
                return true;
            case R.id.i_g:
                fragment.initListFilter(mApiService.filterMeetingByLocation("G"));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}