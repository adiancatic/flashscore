package com.example.flashscore.DataModels;

public class Fixture {

    private int id;
    private int league_id;
    //private Map league
    private String venue;

    public Fixture() {
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

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    @Override
    public String toString() {
        return "Fixture{" +
                "id=" + id +
                ", league_id=" + league_id +
                ", venue='" + venue + '\'' +
                '}';
    }
}
