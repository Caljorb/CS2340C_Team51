package com.example.team_51.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.team_51.model.Leaderboard;
import com.example.team_51.model.LeaderboardRow;

public class LeaderboardViewModel extends ViewModel {
    private Leaderboard leaderboard;
    private LeaderboardRow[] leaderboardRows;

    public LeaderboardViewModel() {
        leaderboardRows = new LeaderboardRow[5];
        for (int i = 0; i < 5; i++) {
            leaderboardRows[i] = new LeaderboardRow("", 0, "");
        }
        leaderboard = Leaderboard.getLeaderboard(leaderboardRows);
    }

    public void setRow(int row, String name, long score, String date) {
        leaderboardRows[row].setName(name);
        leaderboardRows[row].setScore(score);
        leaderboardRows[row].setDate(date);
    }
    // must pick correct row beforehand to put in descending order

}
