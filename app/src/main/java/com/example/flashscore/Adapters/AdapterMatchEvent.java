package com.example.flashscore.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flashscore.DataModels.Fixture;
import com.example.flashscore.DataModels.Standings;
import com.example.flashscore.R;

import java.util.List;
import java.util.Map;

public class AdapterMatchEvent extends RecyclerView.Adapter<AdapterMatchEvent.ViewHolder> {

    List<Map<String, String>> matchEventsItemList;
    private int homeTeamId;
    private int awayTeamId;

    private final int HOME_TEAM = 2;
    private final int AWAY_TEAM = 3;

    public AdapterMatchEvent(List<Map<String, String>> matchEventsItemList, int homeTeamId, int awayTeamId) {
        this.matchEventsItemList = matchEventsItemList;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
    }

    @NonNull
    @Override
    public AdapterMatchEvent.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == HOME_TEAM) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_fixture_event_home, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_fixture_event_away, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        String teamId = matchEventsItemList.get(position).get("teamId");
        if(teamId != null && Integer.parseInt(teamId) == homeTeamId) return HOME_TEAM;
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMatchEvent.ViewHolder holder, int position) {
        holder.eventMinute.setText(matchEventsItemList.get(position).get("elapsed") + "'");

        Map<String, String> event = matchEventsItemList.get(position);

        if(event.get("type").equals("Card") && event.get("detail").equals("Yellow Card")) {
            holder.eventIcon.setImageResource(R.drawable.ic_card_yellow);

            holder.eventPrimaryText.setText(matchEventsItemList.get(position).get("player"));
            holder.eventPrimaryText.setTextColor(Color.BLACK);

            if(matchEventsItemList.get(position).get("comment") != null) {
                holder.eventSecondaryText.setText(matchEventsItemList.get(position).get("comment"));
                holder.eventSecondaryText.setTextColor(ContextCompat.getColor(holder.eventPrimaryText.getContext(), R.color.colorGray));
                holder.eventSecondaryText.setVisibility(View.VISIBLE);

            } else {
                holder.eventSecondaryText.setVisibility(View.GONE);
            }

        } else if(event.get("type").equals("Card") && event.get("detail").equals("Red Card")) {
            holder.eventIcon.setImageResource(R.drawable.ic_card_red);

            holder.eventPrimaryText.setText(matchEventsItemList.get(position).get("player"));
            holder.eventPrimaryText.setTextColor(Color.BLACK);

            if(matchEventsItemList.get(position).get("comment") != null) {
                holder.eventSecondaryText.setText(matchEventsItemList.get(position).get("comment"));
                holder.eventSecondaryText.setTextColor(ContextCompat.getColor(holder.eventPrimaryText.getContext(), R.color.colorGray));
                holder.eventSecondaryText.setVisibility(View.VISIBLE);
            } else {
                holder.eventSecondaryText.setVisibility(View.GONE);
            }

        } else if(event.get("type").equals("Goal") && event.get("detail").equals("Normal Goal")) {
            holder.eventIcon.setImageResource(R.drawable.ic_ball_filled);

            holder.eventPrimaryText.setText(matchEventsItemList.get(position).get("player"));
            holder.eventPrimaryText.setTextColor(Color.BLACK);

            if(matchEventsItemList.get(position).get("assist") != null) {
                holder.eventSecondaryText.setText("assist by " + matchEventsItemList.get(position).get("assist"));
                holder.eventSecondaryText.setTextColor(ContextCompat.getColor(holder.eventPrimaryText.getContext(), R.color.colorGray));
                holder.eventSecondaryText.setVisibility(View.VISIBLE);
            } else {
                holder.eventSecondaryText.setVisibility(View.GONE);
            }

        } else if(event.get("type").equals("Goal") && event.get("detail").equals("Own Goal")) {
            holder.eventIcon.setImageResource(R.drawable.ic_ball_filled_red);

            holder.eventPrimaryText.setText(matchEventsItemList.get(position).get("player"));
            holder.eventPrimaryText.setTextColor(Color.BLACK);

            holder.eventSecondaryText.setVisibility(View.GONE);

        } else if(event.get("type").equals("Penalty") && event.get("detail").equals("Scored")) {
            holder.eventIcon.setImageResource(R.drawable.ic_penalty);

            holder.eventPrimaryText.setText(matchEventsItemList.get(position).get("player"));
            holder.eventPrimaryText.setTextColor(Color.BLACK);

            holder.eventSecondaryText.setVisibility(View.GONE);

        } else if(event.get("type").equals("Penalty") && event.get("detail").equals("Missed")) {
            holder.eventIcon.setImageResource(R.drawable.ic_penalty_missed);

            holder.eventPrimaryText.setText(matchEventsItemList.get(position).get("player"));
            holder.eventPrimaryText.setTextColor(Color.BLACK);

            holder.eventSecondaryText.setVisibility(View.GONE);

        } else if(event.get("type").equals("subst")) {
            holder.eventIcon.setImageResource(R.drawable.ic_substitution);

            holder.eventPrimaryText.setText(matchEventsItemList.get(position).get("player"));
            holder.eventPrimaryText.setTextColor(ContextCompat.getColor(holder.eventPrimaryText.getContext(), R.color.colorPrimary));
            holder.eventSecondaryText.setText(matchEventsItemList.get(position).get("assist"));
            holder.eventSecondaryText.setTextColor(ContextCompat.getColor(holder.eventSecondaryText.getContext(), R.color.colorRed));

        } else {
            holder.eventPrimaryText.setTextColor(ContextCompat.getColor(holder.eventPrimaryText.getContext(), R.color.colorGray));
            holder.eventSecondaryText.setTextColor(ContextCompat.getColor(holder.eventSecondaryText.getContext(), R.color.colorGray));
        }

    }

    @Override
    public int getItemCount() {
        return matchEventsItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView eventMinute;
        ImageView eventIcon;
        TextView eventPrimaryText;
        TextView eventSecondaryText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventMinute = itemView.findViewById(R.id.tv_fixture_event_minute);
            eventIcon = itemView.findViewById(R.id.iv_fixture_event_icon);
            eventPrimaryText = itemView.findViewById(R.id.tv_fixture_event_primary_text);
            eventSecondaryText = itemView.findViewById(R.id.tv_fixture_event_secondary_text);
        }
    }

}
