package com.benjamin.dugas.maru.ui.meeting_list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.benjamin.dugas.maru.DI.DI;
import com.benjamin.dugas.maru.R;
import com.benjamin.dugas.maru.model.Meeting;
import com.benjamin.dugas.maru.service.MeetingApiService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMeetingActivity extends AppCompatActivity {

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.ti_topic)
    TextInputLayout topic;
    @BindView(R.id.rg_location)
    RadioGroup location;
    @BindView(R.id.rg_hour)
    RadioGroup hour;
    @BindView(R.id.tiet_participant)
    TextInputEditText participant;
    @BindView(R.id.mb_add)
    MaterialButton add;

    private MeetingApiService mApiService;
    private List participants = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);
        mApiService = DI.getMeetingApiService();
        int[] androidColors = getResources().getIntArray(R.array.randomColors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        avatar.setColorFilter(randomAndroidColor);
        init();
    }

    private void init() {
        participant.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) { add.setEnabled(s.length() > 0); }
        });

    }

    @OnClick(R.id.mb_add)
    void addParticipant() {
        participants.add(participant.getText().toString());
        participant.setText("");
    }

    @OnClick(R.id.mb_create)
    void addMeeting() {
        RadioButton radioHourButton = (RadioButton) findViewById(hour.getCheckedRadioButtonId());
        RadioButton radioLocationButton = (RadioButton) findViewById(location.getCheckedRadioButtonId());
        if (add.isEnabled())
            participants.add(participant.getText().toString());
        Meeting meeting = new Meeting(
                avatar.getSolidColor(),
                radioHourButton.getText().toString(),
                radioLocationButton.getText().toString(),
                topic.getEditText().getText().toString(),
                participants
        );
        mApiService.createMeeting(meeting);
        finish();
    }
}