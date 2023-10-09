package com.example.team_51.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.team_51.model.LeaderboardRow;

import java.util.ArrayList;
import java.util.Comparator;

public class LeaderboardViewModel extends ViewModel {
    private ArrayList<LeaderboardRow> leaderboardRows;
    private static LeaderboardViewModel leaderboardViewModel;

    private LeaderboardViewModel() {
        leaderboardRows = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            leaderboardRows.set(i, new LeaderboardRow("", 0, ""));
        }
    }

    public static synchronized LeaderboardViewModel getLeaderboardViewModel() {
        if (leaderboardViewModel == null) {
            leaderboardViewModel = new LeaderboardViewModel();
        }
        return leaderboardViewModel;
    }

    public void setRow(int row, LeaderboardRow leaderboardRow) {
        leaderboardRows.set(row, leaderboardRow);
    }

    public void addRow(LeaderboardRow leaderboardRow) {
        leaderboardRows.add(leaderboardRow);
        // adds to end, call sort after
    }

    public void sortRows() {
        leaderboardRows.sort(new Comparator<LeaderboardRow>() {
            @Override
            public int compare(LeaderboardRow leaderboardRow, LeaderboardRow t1) {
                return (int) (leaderboardRow.getScore() - t1.getScore());
            }
        });
    }

    public void clearRows() {
        leaderboardRows.subList(5, leaderboardRows.size()).clear();
    }
}
