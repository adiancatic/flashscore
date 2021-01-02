package com.example.flashscore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchPreview extends AppCompatActivity {

    private int matchId;

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

        homeTeamLogo = findViewById(R.id.iv_fixtures_item_team_logo_home);
        homeTeamName = findViewById(R.id.tv_match_home_team_name);

        awayTeamLogo = findViewById(R.id.iv_fixtures_item_team_logo_away);
        awayTeamName = findViewById(R.id.tv_match_away_team_name);

        homeTeamGoals = findViewById(R.id.tv_match_result_home_team);
        awayTeamGoals = findViewById(R.id.tv_match_result_away_team);
        resultSeparator = findViewById(R.id.tv_match_result_separator);

        elapsed = findViewById(R.id.tv_match_time);

        Intent intent = getIntent();
        matchId = intent.getIntExtra("matchId", -1);

        setContentView(R.layout.activity_match_preview);
    }
}