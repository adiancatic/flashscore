package com.example.flashscore.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flashscore.DataModels.Fixture;
import com.example.flashscore.R;

import java.util.List;

public class AdapterMatches extends RecyclerView.Adapter<AdapterMatches.ViewHolder> {

    List<Fixture> fixtureItemList;

    public AdapterMatches(List<Fixture> fixtureItemList) {
        this.fixtureItemList = fixtureItemList;
    }

    @NonNull
    @Override
    public AdapterMatches.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_fixtures_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMatches.ViewHolder holder, int position) {
        holder.homeTeamName.setText(fixtureItemList.get(position).getHomeTeam().get("teamName"));
        Glide.with(holder.homeTeamLogo.getContext()).load(fixtureItemList.get(position).getHomeTeam().get("teamLogo")).into(holder.homeTeamLogo);
        holder.homeTeamGoals.setText(String.valueOf(fixtureItemList.get(position).getGoalsHomeTeam()));

        holder.awayTeamName.setText(fixtureItemList.get(position).getAwayTeam().get("teamName"));
        Glide.with(holder.awayTeamLogo.getContext()).load(fixtureItemList.get(position).getAwayTeam().get("teamLogo")).into(holder.awayTeamLogo);
        holder.awayTeamGoals.setText(String.valueOf(fixtureItemList.get(position).getGoalsAwayTeam()));

        String elapsed = "";
        if(fixtureItemList.get(position).isStatusHalfTime()) {
            elapsed = "HT";
        } else if(fixtureItemList.get(position).isStatusFullTime()) {
            elapsed = "FT";
        } else {
            elapsed = fixtureItemList.get(position).getElapsed() + "'";
        }
        holder.elapsed.setText(elapsed);
    }

    @Override
    public int getItemCount() {
        return fixtureItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView homeTeamName;
        ImageView homeTeamLogo;
        TextView homeTeamGoals;

        TextView awayTeamName;
        ImageView awayTeamLogo;
        TextView awayTeamGoals;

        TextView elapsed;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            homeTeamName = itemView.findViewById(R.id.tv_fixtures_item_team_name_home);
            homeTeamLogo = itemView.findViewById(R.id.iv_fixtures_item_team_logo_home);
            homeTeamGoals = itemView.findViewById(R.id.tv_fixtures_item_result_home);

            awayTeamName = itemView.findViewById(R.id.tv_fixtures_item_team_name_away);
            awayTeamLogo = itemView.findViewById(R.id.iv_fixtures_item_team_logo_away);
            awayTeamGoals = itemView.findViewById(R.id.tv_fixtures_item_result_away);

            elapsed = itemView.findViewById(R.id.tv_fixtures_item_time);
        }
    }
}