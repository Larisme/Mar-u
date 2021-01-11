package com.benjamin.dugas.maru.ui.meeting_list;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.benjamin.dugas.maru.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_list_avatar)
    public CardView mMeetingAvatar;
    @BindView(R.id.item_list_name)
    public TextView mMeetingInfo;
    @BindView(R.id.tv_participants)
    public TextView mMeetingParticipants;
    @BindView(R.id.item_list_delete_button)
    public ImageButton mDeleteButton;

    public MeetingViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
