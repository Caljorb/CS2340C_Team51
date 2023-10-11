package com.example.team_51.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.team_51.model.LeaderboardRow;

import java.util.ArrayList;
import java.util.Comparator;

public class LeaderboardViewModel extends ViewModel {
    private ArrayList<LeaderboardRow> leaderboardRows;
    private static LeaderboardViewModel leaderboardViewModel;

    private LeaderboardViewModel() {
        leaderboardRows = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            leaderboardRows.add(new LeaderboardRow("", 0, ""));
        }
    }

    public static synchronized LeaderboardViewModel getLeaderboardViewModel() {
        if (leaderboardViewModel == null) {
            leaderboardViewModel = new LeaderboardViewModel();
        }
        return leaderboardViewModel;
    }

    public void setRows(ArrayList<LeaderboardRow> leaderboardRows) {
        this.leaderboardRows = leaderboardRows;
    }

    public void addRow(LeaderboardRow leaderboardRow) {
        leaderboardRows.add(leaderboardRow);
        // adds to end, call sort after
    }

    public void sortRows() { // use for easy sorting
        leaderboardRows.sort(new Comparator<LeaderboardRow>() {
            @Override
            public int compare(LeaderboardRow leaderboardRow, LeaderboardRow t1) {
                return (int) (t1.getScore() - leaderboardRow.getScore());
            }
        });
    }

    // keep only 5 entries in leaderboard data
    public void clearRows() {
        leaderboardRows.subList(5, leaderboardRows.size()).clear();
    }

    public ArrayList<LeaderboardRow> getLeaderboardRows() {
        return leaderboardRows;
    }

    public LeaderboardRow getLeaderboardRow(int index) {
        return leaderboardRows.get(index);
    }

    @Override
    public String toString() {
        String rtn = "";
        for (LeaderboardRow l : leaderboardRows) {
            rtn += " \n" + l.toString();
        }
        return rtn;
    }
}
