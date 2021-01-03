package com.example.flashscore.DataModels;

import java.util.Map;

public class Standings {
    private int rank;
    private int teamId;
    private String teamName;
    private String teamLogo;
    private String forme;
    private Map<String, Integer> statsAll;
    private int goalDifference;
    private int points;

    public Standings() {
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    public String getForme() {
        return forme;
    }

    public void setForme(String forme) {
        this.forme = forme;
    }

    public Map<String, Integer> getStatsAll() {
        return statsAll;
    }

    public void setStatsAll(Map<String, Integer> statsAll) {
        this.statsAll = statsAll;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public String getGoalsDifferenceFull() {
        return statsAll.get("goalsFor") + "/" + statsAll.get("goalsAgainst");
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Standings{" +
                "rank=" + rank +
                ", teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", teamLogo='" + teamLogo + '\'' +
                ", forme='" + forme + '\'' +
                ", statsAll=" + statsAll +
                '}';
    }
}
