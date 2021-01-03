package com.example.flashscore.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flashscore.Adapters.AdapterLeagues;
import com.example.flashscore.Adapters.AdapterMatches;
import com.example.flashscore.Api.FootballApi;
import com.example.flashscore.DataModels.Fixture;
import com.example.flashscore.DataModels.League;
import com.example.flashscore.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLeagues extends Fragment {

    private RecyclerView recyclerView;
    private List<League> leaguesItemList;
    private View view;

    public FragmentLeagues() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leaguesItemList = new ArrayList<>();

        FootballApi footballApi = new FootballApi(this.getActivity());
        footballApi.getLeagues(new FootballApi.LeaguesResponse() {
            @Override
            public void onError(String message) {
                System.out.println("Something wrong");
            }

            @Override
            public void onResponse(List<League> leaguesList) {
                recyclerView = view.findViewById(R.id.rv_leagues_list);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                recyclerView.setAdapter(new AdapterLeagues(leaguesList));

                leaguesItemList = leaguesList;
            }
        }, footballApi.LEAGUE_TYPE_ALL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_leagues, container, false);
        return view;
    }
}