package com.example.team_51.model;

public class Leaderboard {
    private LeaderboardRow[] leaderboardRows;
    private static Leaderboard leaderboard;

    private Leaderboard(LeaderboardRow[] leaderboardRows) {
        this.leaderboardRows = leaderboardRows;
    }

    public static synchronized Leaderboard getLeaderboard(LeaderboardRow[] leaderboardRows) {
        if (leaderboard == null) {
            leaderboard = new Leaderboard(leaderboardRows);
        }
        return leaderboard;
    }
}
