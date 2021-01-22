package com.benjamin.dugas.maru.ui.meeting_list;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.benjamin.dugas.maru.DI.DI;
import com.benjamin.dugas.maru.R;
import com.benjamin.dugas.maru.service.MeetingApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

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
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        ((MeetingFragment) getSupportFragmentManager().findFragmentByTag("meeting")).initListFilter(mApiService.filterMeetingByDate(dayOfMonth,month));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MeetingFragment fragment = (MeetingFragment)getSupportFragmentManager().findFragmentByTag("meeting");
        if (fragment == null)
            return true;
        switch (item.getItemId()) {
            case R.id.i_date:
                DatePickerDialog datePickerDialog = new DatePickerDialog(ListMeetingActivity.this);
                datePickerDialog.setOnDateSetListener(this);
                datePickerDialog.show();
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