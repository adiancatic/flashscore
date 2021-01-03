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
import com.example.flashscore.DataModels.League;
import com.example.flashscore.DataModels.Standings;

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

    private int fixtureId = 0;
    private int leagueId = 0;

    public static int FIXTURE_TYPE_LIVE = 1;
    public static int FIXTURE_TYPE_DATE = 2;
    public static int FIXTURE_TYPE_ID = 3;

    public static int LEAGUE_TYPE_ALL = 11;
    public static int LEAGUE_TYPE_ID = 12;

    public FootballApi(Context context) {
        this.context = context;
        setApiKey();
    }

    private void setApiKey() {
        this.apiKey = "350eaec8e6msha7fa53bf1619c0bp1acaddjsn745164ebc029";
    }

    public void setFixtureId(int fixtureId) {
        this.fixtureId = fixtureId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
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
                            for (int i = 0; i < apiCountryList.length(); i++) {
                                Country country = new Country();
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
     * Leagues
     */

    public interface LeaguesResponse {
        void onError(String message);
        void onResponse(List<League> leaguesList);
    }

    public void getLeagues(final LeaguesResponse leaguesResponse, int type) {
        String url = BASE_URL;
        if(type == LEAGUE_TYPE_ALL) {
            url += Endpoints.LEAGUES;
        } else if(type == LEAGUE_TYPE_ID && leagueId != 0) {
            url += Endpoints.LEAGUES_ID + "/" + leagueId;
        }

        final List<League> leaguesList = new ArrayList<>();

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
                            JSONArray apiLeaguesList = api.getJSONArray("leagues");
                            for (int i = 0; i < apiLeaguesList.length(); i++) {
                                League league = new League();
                                JSONObject apiLeague = (JSONObject) apiLeaguesList.get(i);
                                league.setId(apiLeague.getInt("league_id"));
                                league.setName(apiLeague.getString("name"));
                                league.setType(apiLeague.getString("type"));
                                league.setCountry(apiLeague.getString("country"));
                                league.setCountryCode(apiLeague.getString("country_code"));
                                league.setLogo(apiLeague.getString("logo"));
                                league.setFlag(apiLeague.getString("flag"));
                                leaguesList.add(league);
                            }
                            leaguesResponse.onResponse(leaguesList);
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
                        leaguesResponse.onError("Something wrong");
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-RapidAPI-Key", apiKey);

                return params;
            }
        };

        RequestSingleton.getInstance(context).addToRequestQueue(objectRequest);
    }

    /*
     * Standings
     */

    public interface StandingsResponse {
        void onError(String message);
        void onResponse(List<Standings> standingsList);
    }

    public void getStandings(final StandingsResponse standingsResponse) {
        String url = BASE_URL + "/" + Endpoints.STANDINGS + "/" + leagueId;

        final List<Standings> standingsList = new ArrayList<>();

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
                            JSONArray apiStandingsList = api.getJSONArray("standings").getJSONArray(0);
                            for (int i = 0; i < apiStandingsList.length(); i++) {
                                Standings standings = new Standings();
                                JSONObject apiStandings = (JSONObject) apiStandingsList.get(i);
                                standings.setRank(apiStandings.getInt("rank"));
                                standings.setTeamId(apiStandings.getInt("team_id"));
                                standings.setTeamName(apiStandings.getString("teamName"));
                                standings.setTeamLogo(apiStandings.getString("logo"));
                                standings.setForme(apiStandings.getString("forme"));

                                JSONObject statsAllApi = apiStandings.getJSONObject("all");
                                Map<String, Integer> statsAll = new HashMap<>();
                                statsAll.put("played", statsAllApi.getInt("matchsPlayed"));
                                statsAll.put("wins", statsAllApi.getInt("win"));
                                statsAll.put("draws", statsAllApi.getInt("draw"));
                                statsAll.put("loses", statsAllApi.getInt("lose"));
                                statsAll.put("goalsFor", statsAllApi.getInt("goalsFor"));
                                statsAll.put("goalsAgainst", statsAllApi.getInt("goalsAgainst"));
                                standings.setStatsAll(statsAll);

                                standings.setGoalDifference(apiStandings.getInt("goalsDiff"));
                                standings.setPoints(apiStandings.getInt("points"));

                                standingsList.add(standings);
                            }
                            standingsResponse.onResponse(standingsList);
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
                        standingsResponse.onError("Something wrong");
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
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
        String url = BASE_URL;
        if(type == FIXTURE_TYPE_LIVE) {
            url += Endpoints.FIXTURES_LIVE;
        } else if(type == FIXTURE_TYPE_DATE) {
            url += Endpoints.FIXTURES_DATE + "/" + java.time.LocalDate.now() + "?timezone=Europe/Ljubljana";
        } else if(type == FIXTURE_TYPE_ID && fixtureId != 0) {
            url += Endpoints.FIXTURES_ID + "/" + fixtureId;
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

                                fixture.setEventDate(apiFixture.getLong("event_timestamp"));

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
