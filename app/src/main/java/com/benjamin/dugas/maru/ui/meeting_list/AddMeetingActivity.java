package com.benjamin.dugas.maru.ui.meeting_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.benjamin.dugas.maru.DI.DI;
import com.benjamin.dugas.maru.R;
import com.benjamin.dugas.maru.model.Meeting;
import com.benjamin.dugas.maru.service.MeetingApiService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMeetingActivity extends AppCompatActivity {

    CardView avatar;
    TextInputEditText topic;
    RadioGroup location;
    TimePicker picker;
    TextInputEditText participant;
    Button add;
    TextView list;
    Button create;

    private MeetingApiService mApiService;
    private List participants = new ArrayList();
    private String textParticipants = "";
    private int c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        topic = findViewById(R.id.tiet_topic);
        topic.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_NEXT) {
                    
                }
                return false;
            }
        });

        location = findViewById(R.id.rg_location);

        participant = findViewById(R.id.tiet_participant);
        participant.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    mAddButtonClicked();
                }
                return false;
            }
        });

        avatar = findViewById(R.id.cv_avatar);
        picker = findViewById(R.id.time_picker);
        picker.setIs24HourView(true);

        add = findViewById(R.id.b_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddButtonClicked();
            }
        });

        list = findViewById(R.id.tv_participants);

        create = findViewById(R.id.b_create);
        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCreateButtonClicked();
            }
        });

        mApiService = DI.getMeetingApiService();
        int[] androidColors = getResources().getIntArray(R.array.randomColors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        avatar.setCardBackgroundColor(randomAndroidColor);
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

    public void mAddButtonClicked() {
        participants.add(participant.getText().toString());
        participant.setText("");
        if (c == 0)
            textParticipants = textParticipants + participants.get(c);
        else
            textParticipants = textParticipants + ", " + participants.get(c);
        list.setText(textParticipants);
        c++;
    }

    public void mCreateButtonClicked() {
        int hour = picker.getCurrentHour();
        int minutes = picker.getCurrentMinute();
        RadioButton radioLocationButton = (RadioButton) findViewById(location.getCheckedRadioButtonId());
        if (add.isEnabled())
            participants.add(participant.getText().toString());
        Meeting meeting = new Meeting(
                avatar.getCardBackgroundColor().getDefaultColor(),
                hour,
                minutes,
                radioLocationButton.getText().toString(),
                topic.getText().toString(),
                participants
            );
        for (Meeting checkMeeting : mApiService.getMeeting()) {
            if (checkMeeting.getHour() == meeting.getHour() && checkMeeting.getMinutes() == meeting.getMinutes() || participants.size() < 2) {
                break;
            }
            else {
                mApiService.createMeeting(meeting);
                finish();
            }
        }
    }
}