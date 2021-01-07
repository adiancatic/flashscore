package com.example.flashscore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flashscore.Adapters.AdapterMatches;
import com.example.flashscore.Adapters.AdapterStandings;
import com.example.flashscore.Api.FootballApi;
import com.example.flashscore.DataModels.Fixture;
import com.example.flashscore.DataModels.League;
import com.example.flashscore.DataModels.Standings;

import java.util.List;

public class LeaguePreview extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Standings> standingsItemList;

    private int leagueId;
    private League leagueData;

    ImageView leagueLogo;
    TextView leagueName;
    TextView leagueCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_preview);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(getResources().getString(R.string.leagues));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        leagueLogo = findViewById(R.id.iv_league_logo);
        leagueName = findViewById(R.id.tv_league_name);
        leagueCountry = findViewById(R.id.tv_league_country);

        Intent intent = getIntent();
        leagueId = intent.getIntExtra("leagueId", -1);
    }

    private void setData() {
        Glide.with(leagueLogo.getContext()).load(leagueData.getLogo()).into(leagueLogo);
        leagueName.setText(leagueData.getName());
        leagueCountry.setText(leagueData.getCountry());
    }

    @Override
    protected void onStart() {
        super.onStart();

        FootballApi footballApi = new FootballApi(LeaguePreview.this);
        footballApi.setLeagueId(leagueId);
        footballApi.getLeagues(new FootballApi.LeaguesResponse() {
            @Override
            public void onError(String message) {
                System.out.println("Something wrong");
            }

            @Override
            public void onResponse(List<League> leaguesList) {
                leagueData = leaguesList.get(0);
                setData();
            }
        }, footballApi.LEAGUE_TYPE_ID);

        footballApi.getStandings(new FootballApi.StandingsResponse() {
            @Override
            public void onError(String message) {
                System.out.println("Something wrong");
            }

            @Override
            public void onResponse(List<Standings> standingsList) {
                recyclerView = findViewById(R.id.rv_league_standings);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(LeaguePreview.this));

                recyclerView.setAdapter(new AdapterStandings(standingsList));

                standingsItemList = standingsList;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}