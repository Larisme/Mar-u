package com.benjamin.dugas.maru.ui.meeting_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
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

    CardView avatar;
    TextInputLayout topic;
    RadioGroup location;
    RadioGroup hour;
    TextInputEditText participant;
    Button add;
    Button create;

    private MeetingApiService mApiService;
    private List participants = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        topic = findViewById(R.id.ti_topic);
        location = findViewById(R.id.rg_location);
        hour = findViewById(R.id.rg_hour);
        participant = findViewById(R.id.tiet_participant);
        avatar = findViewById(R.id.cv_avatar);

        add = findViewById(R.id.b_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddButtonClicked();
            }
        });

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
    }

    public void mCreateButtonClicked() {
        RadioButton radioHourButton = (RadioButton) findViewById(hour.getCheckedRadioButtonId());
        RadioButton radioLocationButton = (RadioButton) findViewById(location.getCheckedRadioButtonId());
        if (add.isEnabled())
            participants.add(participant.getText().toString());
        Meeting meeting = new Meeting(
                avatar.getCardBackgroundColor().getDefaultColor(),
                radioHourButton.getText().toString(),
                radioLocationButton.getText().toString(),
                topic.getEditText().getText().toString(),
                participants
        );
        mApiService.createMeeting(meeting);
        Log.i("Test","Color : "+ meeting.getAvatarColor());
        finish();
    }
}