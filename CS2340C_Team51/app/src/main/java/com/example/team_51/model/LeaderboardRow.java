package com.example.team_51.model;

public class LeaderboardRow implements Comparable<LeaderboardRow> {
    private String name;
    private long score;
    private String date;

    public LeaderboardRow(String name, long score, String date) {
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

    @Override
    public int compareTo(LeaderboardRow leaderboardRow) {
        return Integer.compare((int) this.score, (int) leaderboardRow.score);
    }
}
