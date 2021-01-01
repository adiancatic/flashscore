package com.example.flashscore.Api;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.flashscore.DataModels.Country;
import com.example.flashscore.DataModels.Fixture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FootballApi {

    Context context;
    private static final String BASE_URL = "https://api-football-v1.p.rapidapi.com/v2/";
    private String apiKey;

    public static int FIXTURE_TYPE_LIVE = 1;
    public static int FIXTURE_TYPE_DATE = 2;

    public FootballApi(Context context) {
        this.context = context;
        setApiKey();
    }

    private void setApiKey() {
        this.apiKey = "350eaec8e6msha7fa53bf1619c0bp1acaddjsn745164ebc029";
    }

    /*
     * Countries
     */

    public interface CountriesResponse {
        void onError(String message);
        void onResponse(List<Country> countryList);
    }

    public void getCountries(final CountriesResponse countriesResponse) {
        String url = BASE_URL + Endpoints.COUNTRIES;
        final List<Country> countryList = new ArrayList<>();

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Rest Response", response.toString());
                        try {
                            JSONObject api = response.getJSONObject("api");
                            JSONArray apiCountryList = api.getJSONArray("countries");
                            Country country = new Country();
                            for (int i = 0; i < apiCountryList.length(); i++) {
                                JSONObject apiCountry = (JSONObject) apiCountryList.get(i);
                                country.setName(apiCountry.getString("country"));
                                country.setCode(apiCountry.getString("code"));
                                country.setFlag(apiCountry.getString("flag"));
                                countryList.add(country);
                            }
                            countriesResponse.onResponse(countryList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Error", error.toString());
                        error.printStackTrace();
                        countriesResponse.onError("Something wrong");
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("X-RapidAPI-Key", apiKey);

                return params;
            }
        };

        RequestSingleton.getInstance(context).addToRequestQueue(objectRequest);
    }

    /*
     * Fixtures
     */

    public interface FixturesResponse {
        void onError(String message);
        void onResponse(List<Fixture> fixtureList);
    }

    public void getFixtures(final FixturesResponse fixturesResponse, int type) {
        String url = "";
        if(type == FIXTURE_TYPE_LIVE) {
            url = BASE_URL + Endpoints.FIXTURES_LIVE;
        } else if(type == FIXTURE_TYPE_DATE) {
            url = BASE_URL + Endpoints.FIXTURES_DATE + "/" + java.time.LocalDate.now() + "?timezone=Europe/Ljubljana";
        }
        final List<Fixture> fixtureList = new ArrayList<>();

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Rest Response", response.toString());
                        try {
                            JSONObject api = response.getJSONObject("api");
                            JSONArray apiFixtureList = api.getJSONArray("fixtures");
                            for (int i = 0; i < apiFixtureList.length(); i++) {
                                Fixture fixture = new Fixture();

                                JSONObject apiFixture = (JSONObject) apiFixtureList.get(i);
                                fixture.setId(apiFixture.getInt("fixture_id"));
                                fixture.setLeague_id(apiFixture.getInt("league_id"));
                                fixture.setVenue(apiFixture.getString("venue"));

                                JSONObject homeTeamApi = apiFixture.getJSONObject("homeTeam");
                                Map<String, String> homeTeam = new HashMap<>();
                                homeTeam.put("teamId", homeTeamApi.getString("team_id"));
                                homeTeam.put("teamName", homeTeamApi.getString("team_name"));
                                homeTeam.put("teamLogo", homeTeamApi.getString("logo"));
                                fixture.setHomeTeam(homeTeam);

                                JSONObject awayTeamApi = apiFixture.getJSONObject("awayTeam");
                                Map<String, String> awayTeam = new HashMap<>();
                                awayTeam.put("teamId", awayTeamApi.getString("team_id"));
                                awayTeam.put("teamName", awayTeamApi.getString("team_name"));
                                awayTeam.put("teamLogo", awayTeamApi.getString("logo"));
                                fixture.setAwayTeam(awayTeam);

                                fixture.setGoalsHomeTeam(0);
                                if(!apiFixture.isNull("goalsHomeTeam")) {
                                    fixture.setGoalsHomeTeam(apiFixture.getInt("goalsHomeTeam"));
                                }
                                fixture.setGoalsAwayTeam(0);
                                if(!apiFixture.isNull("goalsAwayTeam")) {
                                    fixture.setGoalsAwayTeam(apiFixture.getInt("goalsAwayTeam"));
                                }

                                fixture.setElapsed(apiFixture.getInt("elapsed"));
                                fixture.setStatus(apiFixture.getString("status"));

                                fixtureList.add(fixture);
                            }
                            fixturesResponse.onResponse(fixtureList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Error", error.toString());
                        error.printStackTrace();
                        fixturesResponse.onError("Something wrong");
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("X-RapidAPI-Key", apiKey);

                return params;
            }
        };

        RequestSingleton.getInstance(context).addToRequestQueue(objectRequest);
    }

}
