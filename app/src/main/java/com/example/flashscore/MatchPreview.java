package com.example.flashscore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flashscore.Api.FootballApi;
import com.example.flashscore.DataModels.Country;
import com.example.flashscore.DataModels.Fixture;

import java.util.List;

public class MatchPreview extends AppCompatActivity {

    private int matchId;
    private Fixture matchData;

    ImageView homeTeamLogo;
    TextView homeTeamName;

    ImageView awayTeamLogo;
    TextView awayTeamName;

    TextView homeTeamGoals;
    TextView awayTeamGoals;
    TextView resultSeparator;

    TextView elapsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_preview);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("");
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            actionBar.setElevation(0);
            actionBar.setDisplayHomeAsUpEnabled(true);

            final Drawable arrowBack = ResourcesCompat.getDrawable(this.getResources(), R.drawable.ic_baseline_arrow_back_24, this.getTheme());
            actionBar.setHomeAsUpIndicator(arrowBack);
        }

        homeTeamLogo = findViewById(R.id.iv_match_home_team_logo);
        homeTeamName = findViewById(R.id.tv_match_home_team_name);

        awayTeamLogo = findViewById(R.id.iv_match_away_team_logo);
        awayTeamName = findViewById(R.id.tv_match_away_team_name);

        homeTeamGoals = findViewById(R.id.tv_match_result_home_team);
        awayTeamGoals = findViewById(R.id.tv_match_result_away_team);
        resultSeparator = findViewById(R.id.tv_match_result_separator);

        elapsed = findViewById(R.id.tv_match_time);

        Intent intent = getIntent();
        matchId = intent.getIntExtra("matchId", -1);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FootballApi footballApi = new FootballApi(MatchPreview.this);
        footballApi.setFixtureId(matchId);
        footballApi.getFixtures(new FootballApi.FixturesResponse() {
            @Override
            public void onError(String message) {
                System.out.println("Something wrong");
            }

            @Override
            public void onResponse(List<Fixture> fixtureList) {
                // System.out.println(fixtureList);
                matchData = fixtureList.get(0);
                setData();
            }
        }, footballApi.FIXTURE_TYPE_ID);
    }

    private void setData() {
        Glide.with(MatchPreview.this).load(matchData.getHomeTeam().get("teamLogo")).into(homeTeamLogo);
        homeTeamName.setText(matchData.getHomeTeam().get("teamName"));

        Glide.with(MatchPreview.this).load(matchData.getAwayTeam().get("teamLogo")).into(awayTeamLogo);
        awayTeamName.setText(matchData.getAwayTeam().get("teamName"));

        homeTeamGoals.setText(matchData.getHomeResultString());
        awayTeamGoals.setText(matchData.getAwayResultString());
        resultSeparator.setText(matchData.getSeparatorResultString());

        elapsed.setText(matchData.getElapsedString());
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