package com.example.flashscore.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flashscore.DataModels.Standings;
import com.example.flashscore.R;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterStandings extends RecyclerView.Adapter<AdapterStandings.ViewHolder> {

    List<Standings> standingsItemList;

    public AdapterStandings(List<Standings> standingsItemList) {
        this.standingsItemList = standingsItemList;
    }

    @NonNull
    @Override
    public AdapterStandings.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_standings_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterStandings.ViewHolder holder, int position) {
        holder.rank.setText(String.valueOf(standingsItemList.get(position).getRank()));
        Glide.with(holder.teamLogo.getContext()).load(standingsItemList.get(position).getTeamLogo()).into(holder.teamLogo);
        holder.teamName.setText(standingsItemList.get(position).getTeamName());
        holder.played.setText(String.valueOf(standingsItemList.get(position).getStatsAll().get("played")));
        holder.wins.setText(String.valueOf(standingsItemList.get(position).getStatsAll().get("wins")));
        holder.draws.setText(String.valueOf(standingsItemList.get(position).getStatsAll().get("draws")));
        holder.loses.setText(String.valueOf(standingsItemList.get(position).getStatsAll().get("loses")));
        holder.goalsDifferenceLong.setText(standingsItemList.get(position).getGoalsDifferenceFull());
        holder.goalsDifference.setText(String.valueOf(standingsItemList.get(position).getGoalDifference()));
        holder.points.setText(String.valueOf(standingsItemList.get(position).getPoints()));
    }

    @Override
    public int getItemCount() {
        return standingsItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rank;
        ImageView teamLogo;
        TextView teamName;
        TextView played;
        TextView wins;
        TextView draws;
        TextView loses;
        TextView goalsDifferenceLong;
        TextView goalsDifference;
        TextView points;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rank = itemView.findViewById(R.id.tv_standings_item_rank);
            teamLogo = itemView.findViewById(R.id.iv_standings_item_team_logo);
            teamName = itemView.findViewById(R.id.tv_standings_item_team_name);
            played = itemView.findViewById(R.id.tv_standings_item_played);
            wins = itemView.findViewById(R.id.tv_standings_item_wins);
            draws = itemView.findViewById(R.id.tv_standings_item_draws);
            loses = itemView.findViewById(R.id.tv_standings_item_loses);
            goalsDifferenceLong = itemView.findViewById(R.id.tv_standings_item_goal_difference_long);
            goalsDifference = itemView.findViewById(R.id.tv_standings_item_goal_difference);
            points = itemView.findViewById(R.id.tv_standings_item_points);
        }
    }

}
