package com.example.flashscore.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flashscore.Adapters.AdapterMoreActions;
import com.example.flashscore.MoreActionsItem;
import com.example.flashscore.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMoreActions extends Fragment {

    private RecyclerView recyclerView;
    private List<MoreActionsItem> moreActionsItemList;

    public FragmentMoreActions() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more_actions, container, false);

        recyclerView = view.findViewById(R.id.rv_more_actions_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(new AdapterMoreActions(initData()));

        return view;
    }

    private List<MoreActionsItem> initData() {
        moreActionsItemList = new ArrayList<>();
        // moreActionsItemList.add(new MoreActionsItem(R.drawable.ic_football_ball, "Option 1"));
        // moreActionsItemList.add(new MoreActionsItem(R.drawable.ic_football_ball, "Option 2"));
        // moreActionsItemList.add(new MoreActionsItem(R.drawable.ic_football_ball, "Option 3"));
        return moreActionsItemList;
    }

}