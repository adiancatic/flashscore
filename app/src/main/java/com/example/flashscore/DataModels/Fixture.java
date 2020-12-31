package com.example.flashscore.DataModels;

import java.util.Map;

public class Fixture {

    private int id;
    private int league_id;
    private Map<String, String> homeTeam;
    private Map<String, String> awayTeam;
    private int goalsHomeTeam;
    private int goalsAwayTeam;
    private int elapsed;

    private String venue;

    public Fixture() {
    }

    public Fixture(Map<String, String> homeTeam, Map<String, String> awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    @Override
    public String toString() {
        return "Fixture{" +
                "id=" + id +
                ", league_id=" + league_id +
                ", venue='" + venue + '\'' +
                ", homeTeam=" + homeTeam.get("teamName") +
                ", awayTeam=" + awayTeam.get("teamName") +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeague_id() {
        return league_id;
    }

    public void setLeague_id(int league_id) {
        this.league_id = league_id;
    }

    public Map<String, String> getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Map<String, String> homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Map<String, String> getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Map<String, String> awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public void setGoalsHomeTeam(int goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    public int getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public void setGoalsAwayTeam(int goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }

    public int getElapsed() {
        return elapsed;
    }

    public void setElapsed(int elapsed) {
        this.elapsed = elapsed;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
