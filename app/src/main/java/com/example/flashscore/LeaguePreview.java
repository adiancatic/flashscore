package com.example.flashscore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flashscore.Api.FootballApi;
import com.example.flashscore.DataModels.Fixture;
import com.example.flashscore.DataModels.League;

import java.util.List;

public class LeaguePreview extends AppCompatActivity {

    private int leagueId;
    private League leagueData;

    TextView leagueName;
    ImageView leagueLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_preview);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            // actionBar.setTitle("");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        leagueName = findViewById(R.id.tv_league_name);

        Intent intent = getIntent();
        leagueId = intent.getIntExtra("leagueId", -1);
    }

    private void setData() {
        leagueName.setText(leagueData.getName());
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
                // System.out.println(fixtureList);
                leagueData = leaguesList.get(0);
                setData();
            }
        }, footballApi.LEAGUE_TYPE_ID);
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