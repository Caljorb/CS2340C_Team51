package com.example.cs2340c_team51;

import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Context;

import com.example.team_51.model.Button;
import com.example.team_51.model.LeaderboardRow;
import com.example.team_51.model.Player;
import com.example.team_51.model.map.Tilemap;
import com.example.team_51.viewmodels.LeaderboardViewModel;
import com.example.team_51.viewmodels.SpriteSheet;

import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    } // example test for reference

    // Method for score not going below 0 here

    // Method for Leaderboard sort scores properly here

    // Method for score being put in leaderboard even when score == 0

    // Method to check name has NO whitespace at all, no null name, or empty name

    // Method to check starting health based on difficulty

    // Method to check that leaderboardViewModel is a singleton (Caleb)
    @Test
    public void isLeaderboardSingleton() {
        LeaderboardViewModel leaderboardViewModel1 = LeaderboardViewModel.getLeaderboardViewModel();

        int address1 = System.identityHashCode(leaderboardViewModel1);
        System.out.println("Memory: " + address1);

        LeaderboardViewModel leaderboardViewModel2 = LeaderboardViewModel.getLeaderboardViewModel();
        int address2 = System.identityHashCode(leaderboardViewModel2);
        System.out.println("Memory: " + address2);

        assertEquals("Addresses should point to same spot in memory.", address1, address2);

        String beforeSet = leaderboardViewModel1.toString();
        System.out.println(beforeSet);
        leaderboardViewModel2.setRows(makeTestRows());
        String afterSet = leaderboardViewModel1.toString();
        System.out.println(afterSet);

        assertNotEquals("Changing 2nd reference should also change 1st" +
                " because they reference same instance", beforeSet, afterSet);

        assertEquals("Changing one should change both", afterSet,
                leaderboardViewModel2.toString());
    }

    // Method to check that selected character picks correct sprite (Caleb)
    @Test
    public void correctSprite() {
        // idk
    }

    // Method to check selecting difficulty updates the text for difficulty


    private ArrayList<LeaderboardRow> makeTestRows() {
        ArrayList<LeaderboardRow> leaderboardRows = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            leaderboardRows.add(new LeaderboardRow("Bob" + i, i * 2, "Today"));
        }
        return leaderboardRows;
    }
}
