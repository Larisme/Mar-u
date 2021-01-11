package com.benjamin.dugas.maru.ui.meeting_list;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.benjamin.dugas.maru.DI.DI;
import com.benjamin.dugas.maru.R;
import com.benjamin.dugas.maru.model.Meeting;
import com.benjamin.dugas.maru.service.MeetingApiService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT) {
                    hideKeyboard(AddMeetingActivity.this);
                    handled = true;
                }
                return handled;
            }
        });

        location = findViewById(R.id.rg_location);

        participant = findViewById(R.id.tiet_participant);
        participant.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    mAddButtonClicked();
                    handled = true;
                }
                return handled;
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
        create.setEnabled(false);

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

        topic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) { mCreateButtonEnable(); }
        });

        location.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { mCreateButtonEnable(); }
        });
    }

    public void mCreateButtonEnable() {
        boolean topicCheck = topic.getText() != null && topic.getText().toString().length() > 0;
        boolean locationCheck = location.getCheckedRadioButtonId() != -1;
        create.setEnabled(topicCheck && locationCheck);
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
        boolean checkGoodMeeting = false;
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

        if (mApiService.getMeeting().size() > 0) {
            for (Meeting checkMeeting : mApiService.getMeeting()) {
                if (checkMeeting.getHour() == meeting.getHour() && checkMeeting.getMinutes() == meeting.getMinutes() || participants.size() < 2) {
                    Toast.makeText(getApplicationContext(), "Your meeting already exists or you need 2 more participants !", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    checkGoodMeeting = true;
                    break;
                }
            }
        }
        else if (participants.size() < 2)
            Toast.makeText(getApplicationContext(), "You need 2 participants minimum !", Toast.LENGTH_SHORT).show();
        else
            checkGoodMeeting = true;

        if (checkGoodMeeting) {
            mApiService.createMeeting(meeting);
            finish();
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}