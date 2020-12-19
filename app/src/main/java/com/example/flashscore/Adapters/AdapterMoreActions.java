package com.example.flashscore.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashscore.MoreActionsItem;
import com.example.flashscore.R;

import java.util.List;

public class AdapterMoreActions extends RecyclerView.Adapter<AdapterMoreActions.ViewHolder> {

    List<MoreActionsItem> moreActionsItemList;

    public AdapterMoreActions(List<MoreActionsItem> moreActionsItemList) {
        this.moreActionsItemList = moreActionsItemList;
    }

    @NonNull
    @Override
    public AdapterMoreActions.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_more_actions_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMoreActions.ViewHolder holder, int position) {
        holder.actionIcon.setImageResource(moreActionsItemList.get(position).getIcon());
        holder.actionName.setText(moreActionsItemList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return moreActionsItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView actionIcon;
        TextView actionName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            actionIcon = itemView.findViewById(R.id.iv_more_actions_item_icon);
            actionName = itemView.findViewById(R.id.tv_more_actions_item_name);
        }
    }

}
