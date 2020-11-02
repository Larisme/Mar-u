package com.benjamin.dugas.maru.ui.meeting_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.benjamin.dugas.maru.R;
import com.benjamin.dugas.maru.events.DeleteMeetingEvent;
import com.benjamin.dugas.maru.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingViewHolder> {

    private final List<Meeting> mMeetings;

    public MeetingAdapter(List<Meeting> mMeetings) {
        this.mMeetings = mMeetings;
    }

    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_meeting, parent, false);
        return new MeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MeetingViewHolder viewHolder, int position){
        final Meeting meeting = mMeetings.get(position);
        viewHolder.mMeetingInfo.setText(String.format("Réunion %s - %s - %s", meeting.getLocation(), meeting.getHour(), meeting.getTopic()));
        viewHolder.mMeetingParticipants.setText(String.format("%s, %s...", meeting.getParticipant(0), meeting.getParticipant(1)));

        viewHolder.mDeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));

                /* mMeetings.remove(meeting);
                notifyDataSetChanged(); */
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

}
