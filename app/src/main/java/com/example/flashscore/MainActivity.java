package com.example.flashscore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.flashscore.Api.FootballApi;
import com.example.flashscore.DataModels.Country;

import java.util.List;

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
    }

}