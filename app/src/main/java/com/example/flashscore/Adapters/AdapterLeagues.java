package com.example.flashscore.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flashscore.DataModels.League;
import com.example.flashscore.LeaguePreview;
import com.example.flashscore.MatchPreview;
import com.example.flashscore.R;

import java.util.List;

public class AdapterLeagues extends RecyclerView.Adapter<AdapterLeagues.ViewHolder> {

    List<League> leaguesItemList;

    public AdapterLeagues(List<League> leaguesItemList) {
        this.leaguesItemList = leaguesItemList;
    }

    @NonNull
    @Override
    public AdapterLeagues.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_leagues_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLeagues.ViewHolder holder, int position) {
        Glide.with(holder.leagueLogo.getContext()).load(leaguesItemList.get(position).getLogo()).into(holder.leagueLogo);
        holder.leagueName.setText(leaguesItemList.get(position).getName());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.parentLayout.getContext(), LeaguePreview.class);
                intent.putExtra("leagueId", leaguesItemList.get(position).getId());
                holder.parentLayout.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return leaguesItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout parentLayout;
        ImageView leagueLogo;
        TextView leagueName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.league_item);
            leagueLogo = itemView.findViewById(R.id.iv_league_item_logo);
            leagueName = itemView.findViewById(R.id.tv_league_item_name);
        }
    }

}
