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
        Glide.with(holder.teamLogo.getContext()).load(standingsItemList.get(position).getTeamLogo()).into(holder.teamLogo);
        holder.teamName.setText(standingsItemList.get(position).getTeamName());
    }

    @Override
    public int getItemCount() {
        return standingsItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView teamLogo;
        TextView teamName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            teamLogo = itemView.findViewById(R.id.iv_standings_item_team_logo);
            teamName = itemView.findViewById(R.id.tv_standings_item_team_name);
        }
    }

}
