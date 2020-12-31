package com.example.flashscore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.flashscore.Api.FootballApi;
import com.example.flashscore.DataModels.Country;
import com.example.flashscore.DataModels.Fixture;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    FootballApi footballApi = new FootballApi(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        footballApi.getCountries(new FootballApi.CountriesResponse() {
            @Override
            public void onError(String message) {
                System.out.println("Something wrong");
            }

            @Override
            public void onResponse(List<Country> countryList) {
                System.out.println(countryList);
            }
        });


        /*
         * Navigation
         */
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        Set<Integer> topLevelDestinations = new HashSet<>();
        topLevelDestinations.add(R.id.fr_matches_all);
        topLevelDestinations.add(R.id.fr_matches_live);
        topLevelDestinations.add(R.id.fr_more_actions);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestinations).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

}