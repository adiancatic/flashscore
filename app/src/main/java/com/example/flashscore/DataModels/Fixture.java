package com.example.flashscore.DataModels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Fixture {

    private int id;
    private int league_id;
    private Map<String, String> homeTeam;
    private Map<String, String> awayTeam;
    private int goalsHomeTeam;
    private int goalsAwayTeam;
    private int elapsed;
    private String status;
    private Date eventDate;
    private List<Map<String, String>> events;

    private String venue;

    public Fixture() {

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isStatusHalfTime() {
        return this.status.equals("Halftime");
    }

    public boolean isStatusFullTime() {
        return this.status.equals("Match Finished");
    }

    public boolean isStatusNotStarted() {
        return this.status.equals("Not Started");
    }

    public boolean isStatusPostponed() {
        return this.status.equals("Match Postponed");
    }

    public boolean isStatusTimeToBeDefined() {
        return this.status.equals("Time to be defined");
    }

    public String getElapsedString() {
        if(this.isStatusNotStarted() || this.isStatusPostponed() || this.isStatusTimeToBeDefined() || this.isStatusNotStarted()) {
            return "";
        } else if(this.isStatusHalfTime()) {
            return "HT";
        } else if(this.isStatusFullTime()) {
            return "FT";
        } else {
            return this.getElapsed() + "'";
        }
    }

    public String getHomeResultString() {
        if(this.isStatusNotStarted() || this.isStatusPostponed() || this.isStatusTimeToBeDefined() || this.isStatusNotStarted()) {
            return "";
        } else {
            return String.valueOf(this.getGoalsHomeTeam());
        }
    }

    public String getAwayResultString() {
        if(this.isStatusNotStarted() || this.isStatusPostponed() || this.isStatusTimeToBeDefined() || this.isStatusNotStarted()) {
            return "";
        } else {
            return String.valueOf(this.getGoalsAwayTeam());
        }
    }

    public String getSeparatorResultString() {
        if(this.isStatusNotStarted()) {
            return this.getEventDateStartTime();
        } else if(this.isStatusPostponed()) {
            return "PST";
        } else if(this.isStatusTimeToBeDefined()) {
            return "TBD";
        } else if(this.isStatusNotStarted()) {
            return "NS";
        } else {
            return "-";
        }
    }

    public String getEventDateFull() {
        DateFormat dateFormat = new SimpleDateFormat("dd. MM. YYYY");
        return dateFormat.format(eventDate);
    }

    public String getEventDateStartTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(eventDate);
    }

    public void setEventDate(long eventTimestamp) {
        this.eventDate = new Date(eventTimestamp * 1000L);
    }

    public List<Map<String, String>> getEvents() {
        return events;
    }

    public void setEvents(List<Map<String, String>> events) {
        this.events = events;
    }
}
