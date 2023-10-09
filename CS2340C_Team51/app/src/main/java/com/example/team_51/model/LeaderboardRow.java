package com.example.team_51.model;

public class LeaderboardRow {
    private String name;
    private long score;
    private String date;

    LeaderboardRow(String name, long score, String date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public long getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
