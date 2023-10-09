package com.example.team_51.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.team_51.model.Leaderboard;
import com.example.team_51.model.LeaderboardRow;

public class LeaderboardViewModel extends ViewModel {
    private Leaderboard leaderboard;
    private LeaderboardRow[] leaderboardRows;
    private static LeaderboardViewModel leaderboardViewModel;

    private LeaderboardViewModel() {
        leaderboardRows = new LeaderboardRow[5];
        for (int i = 0; i < 5; i++) {
            leaderboardRows[i] = new LeaderboardRow("", 0, "");
        }
        leaderboard = Leaderboard.getLeaderboard(leaderboardRows);
    }

    public static synchronized LeaderboardViewModel getLeaderboardViewModel() {
        if (leaderboardViewModel == null) {
            leaderboardViewModel = new LeaderboardViewModel();
        }
        return leaderboardViewModel;
    }

    public void setRow(int row, LeaderboardRow leaderboardRow) {
        leaderboardRows[row] = leaderboardRow;
        // must pick correct row beforehand to put in descending order
    }
}
