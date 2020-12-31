package com.example.flashscore.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flashscore.Adapters.AdapterMatchesLive;
import com.example.flashscore.Api.FootballApi;
import com.example.flashscore.DataModels.Fixture;
import com.example.flashscore.MainActivity;
import com.example.flashscore.MoreActionsItem;
import com.example.flashscore.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMatchesLive extends Fragment {

    private RecyclerView recyclerView;
    private List<Fixture> fixturesItemList;
    private View view;

    public FragmentMatchesLive() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_matches_live, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fixturesItemList = new ArrayList<>();

        FootballApi footballApi = new FootballApi(this.getActivity());
        footballApi.getFixtures(new FootballApi.FixturesResponse() {
            @Override
            public void onError(String message) {
                System.out.println("Something wrong");
            }

            @Override
            public void onResponse(List<Fixture> fixtureList) {
                recyclerView = view.findViewById(R.id.rv_matches_live_list);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                recyclerView.setAdapter(new AdapterMatchesLive(fixtureList));

                fixturesItemList = fixtureList;
            }
        });
    }

}