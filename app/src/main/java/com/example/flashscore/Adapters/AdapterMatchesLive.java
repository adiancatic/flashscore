package com.example.flashscore.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashscore.DataModels.Fixture;
import com.example.flashscore.MoreActionsItem;
import com.example.flashscore.R;

import java.util.List;

public class AdapterMatchesLive extends RecyclerView.Adapter<AdapterMatchesLive.ViewHolder> {

    List<Fixture> fixtureItemList;

    public AdapterMatchesLive(List<Fixture> fixtureItemList) {
        this.fixtureItemList = fixtureItemList;
    }

    @NonNull
    @Override
    public AdapterMatchesLive.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_fixtures_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMatchesLive.ViewHolder holder, int position) {
        holder.homeTeamName.setText(fixtureItemList.get(position).getHomeTeam().get("teamName"));
        holder.awayTeamName.setText(fixtureItemList.get(position).getAwayTeam().get("teamName"));
    }

    @Override
    public int getItemCount() {
        return fixtureItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView homeTeamName;
        TextView awayTeamName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            homeTeamName = itemView.findViewById(R.id.tv_fixtures_item_team_name_home);
            awayTeamName = itemView.findViewById(R.id.tv_fixtures_item_team_name_away);
        }
    }
}
