package com.example.flashscore.Adapters;

import android.content.Context;
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

        holder.awayTeamName.setText(fixtureItemList.get(position).getAwayTeam().get("teamName"));
        Glide.with(holder.awayTeamLogo.getContext()).load(fixtureItemList.get(position).getAwayTeam().get("teamLogo")).into(holder.awayTeamLogo);

        holder.resultSeparator.setText(fixtureItemList.get(position).getSeparatorResultString());
        holder.homeTeamGoals.setText(fixtureItemList.get(position).getHomeResultString());
        holder.awayTeamGoals.setText(fixtureItemList.get(position).getAwayResultString());

        holder.elapsed.setText(fixtureItemList.get(position).getElapsedString());
        if(fixtureItemList.get(position).isStatusFullTime()) {
            holder.elapsed.setTextColor(ContextCompat.getColor(holder.elapsed.getContext(), R.color.colorGray));
        } else {
            holder.elapsed.setTextColor(ContextCompat.getColor(holder.elapsed.getContext(), R.color.colorPrimary));
        }
    }

    @Override
    public int getItemCount() {
        return fixtureItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView homeTeamName;
        ImageView homeTeamLogo;

        TextView awayTeamName;
        ImageView awayTeamLogo;

        TextView homeTeamGoals;
        TextView awayTeamGoals;
        TextView resultSeparator;

        TextView elapsed;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            homeTeamName = itemView.findViewById(R.id.tv_fixtures_item_team_name_home);
            homeTeamLogo = itemView.findViewById(R.id.iv_fixtures_item_team_logo_home);

            awayTeamName = itemView.findViewById(R.id.tv_fixtures_item_team_name_away);
            awayTeamLogo = itemView.findViewById(R.id.iv_fixtures_item_team_logo_away);

            homeTeamGoals = itemView.findViewById(R.id.tv_fixtures_item_result_home);
            awayTeamGoals = itemView.findViewById(R.id.tv_fixtures_item_result_away);
            resultSeparator = itemView.findViewById(R.id.tv_fixtures_item_result_separator);

            elapsed = itemView.findViewById(R.id.tv_fixtures_item_time);
        }
    }
}
