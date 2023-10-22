package com.example.cs2340c_team51;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.example.team_51.model.LeaderboardRow;
import com.example.team_51.model.MoveBall;
import com.example.team_51.model.Player;
import com.example.team_51.model.Game;
import com.example.team_51.viewmodels.LeaderboardViewModel;
import com.example.team_51.viewmodels.SpriteSheet;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    // Method for score not going below 0 here (Rashmith)

    @Test
    public void scoreCheck() {
        Game game = new Game(100, "rashmith", 1, 1000);
        assertTrue(game.checkPoints(game.getPoints()));

        Game game1 = new Game(80, "daniel", 2, 0);
        assertTrue(game1.checkPoints(game1.getPoints()));

        Game game2 = new Game(30, "caleb", 3, -15000);
        assertFalse(game2.checkPoints(game2.getPoints()));

    }


    // Method for Leaderboard sort scores properly here (Rashmith)

    @Test
    public void leaderboardSortCheck() {
        ArrayList<LeaderboardRow> leaderboardRows = new ArrayList<>();
        leaderboardRows.add(new LeaderboardRow("lr1", 100, "10/11/23"));
        leaderboardRows.add(new LeaderboardRow("lr2", 28, "10/11/23"));
        leaderboardRows.add(new LeaderboardRow("lr3", 672, "10/12/23"));
        leaderboardRows.add(new LeaderboardRow("lr4", 1000, "10/13/23"));
        leaderboardRows.add(new LeaderboardRow("lr5", 0, "10/13/23"));

        ArrayList<LeaderboardRow> leaderboardRowsCorrect = new ArrayList<>();
        leaderboardRowsCorrect.add(new LeaderboardRow("lr4", 1000, "10/13/23"));
        leaderboardRowsCorrect.add(new LeaderboardRow("lr3", 672, "10/12/23"));
        leaderboardRowsCorrect.add(new LeaderboardRow("lr1", 100, "10/11/23"));
        leaderboardRowsCorrect.add(new LeaderboardRow("lr2", 28, "10/11/23"));
        leaderboardRowsCorrect.add(new LeaderboardRow("lr5", 0, "10/13/23"));

        LeaderboardViewModel lr = LeaderboardViewModel.getLeaderboardViewModel();
        lr.setRows(leaderboardRows);
        lr.sortRows();

        String temp1 = lr.toString();
        lr.setRows(leaderboardRowsCorrect);
        String temp2 = lr.toString();

        assertEquals(temp1, temp2);
    }

    // Method to check name has NO whitespace at all, no null name, or empty name
    @Test
    public void nameCheck() {
        int[] hpChar1 = new int[]{100, 1};
        Player player = Player.getPlayer(null, 0, 0, 0, null,
                new SpriteSheet(null), hpChar1);

        assertFalse(player.checkName(player.getName()));

        player.setName(" dLee");
        assertFalse(player.checkName(player.getName()));

        player.setName("daniel lee");
        assertFalse(player.checkName(player.getName()));

        player.setName(" ");
        assertFalse(player.checkName(player.getName()));

        player.setName("");
        assertFalse(player.checkName(player.getName()));

        player.setName("danielLee");
        assertTrue(player.checkName(player.getName()));
    }

    // Method to check starting health based on difficulty - Daniel
    @Test
    public void diffCheck() {
        Game game1 = new Game(100, "daniel", 1, 0);
        Player player1 = game1.getPlayer();
        int hp1 = player1.getHp();
        assertEquals("Hp does not correspond with easy difficulty", hp1, 100);


        Game game2 = new Game(50, "daniel", 1, 0);
        Player player2 = game2.getPlayer();
        int hp2 = player2.getHp();
        assertEquals("Hp does not correspond with medium difficulty", hp2, 50);

        Game game3 = new Game(30, "daniel", 1, 0);
        Player player3 = game3.getPlayer();
        int hp3 = player3.getHp();
        assertEquals("Hp does not correspond with hard difficulty", hp3, 30);

    }

    // Method to check that leaderboardViewModel is a singleton (Caleb)
    @Test
    public void isLeaderboardSingleton() {
        // check making two pointers point to same address
        LeaderboardViewModel leaderboardViewModel1 = LeaderboardViewModel.getLeaderboardViewModel();
        int address1 = System.identityHashCode(leaderboardViewModel1);
        System.out.println("Memory: " + address1);

        LeaderboardViewModel leaderboardViewModel2 = LeaderboardViewModel.getLeaderboardViewModel();
        int address2 = System.identityHashCode(leaderboardViewModel2);
        System.out.println("Memory: " + address2);

        assertEquals("Addresses should point to same spot in memory.", address1, address2);

        // check changing one changes other (1 instance)
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

    // Method to check player is a singleton (Caleb)
    @Test
    public void isPlayerSingleton() {
        // check making two pointers point to same address
        int[] hpChar1 = new int[]{100, 1};
        Player player1 = Player.getPlayer(null, 0, 0,0 ,"Bob",
                new SpriteSheet(null), hpChar1);
        int address1 = System.identityHashCode(player1);
        System.out.println("Memory: " + address1);

        Player player2 = Player.getPlayer(null, 0, 0,0 ,"Bob",
                new SpriteSheet(null), hpChar1);
        int address2 = System.identityHashCode(player2);
        System.out.println("Memory: " + address2);

        assertEquals("Addresses should point to same spot in memory.", address1, address2);

        // Trying to get a player with a different name updates name
        int[] hpChar2 = new int[]{50, 2};
        String beforeSet = player1.getName();
        System.out.println(beforeSet);
        player2 = Player.getPlayer(null, 0, 0,0,
                "Joe", new SpriteSheet(null), hpChar2);
        String afterSet = player1.getName();
        System.out.println(afterSet);

        assertNotEquals("Changing 2nd reference should also change 1st" +
                " because they reference same instance", beforeSet, afterSet);

        // Check that is same address
        int address3 = System.identityHashCode(player2);

        assertEquals("Addresses should be same even though data was updated.",
                address1, address3);
    }
    // Method to check selecting difficulty updates the text for difficulty (Kavya)
    @Test
    public void diffUpdateCheck() {
        Game game = new Game(100, "g1", 1, 0);
        String temp = game.diffSelect(game.getDiff());
        game.setDiff(50);
        String temp1 = game.diffSelect(game.getDiff());
        assertNotEquals(temp, temp1);
        game.setDiff(30);
        String temp2 = game.diffSelect(game.getDiff());
        assertNotEquals(temp, temp2);
    }

    // Method for score being put in leaderboard even when score == 0 (Kavya)
    @Test
    public void checkLbScoreZero() {
        LeaderboardViewModel lb = LeaderboardViewModel.getLeaderboardViewModel();
        String st = lb.toString();
        ArrayList<LeaderboardRow> leaderboardRows = new ArrayList<>();
        LeaderboardRow temp1 = new LeaderboardRow("row1", 0, "10/10/23");
        LeaderboardRow temp2 = new LeaderboardRow("row2", 0, "09/10/23");
        LeaderboardRow temp3 = new LeaderboardRow("row3", 0, "08/10/23");
        LeaderboardRow temp4 = new LeaderboardRow("row4", 0, "07/10/23");
        LeaderboardRow temp5 = new LeaderboardRow("row5", 0, "06/10/23");
        leaderboardRows.add(temp1);
        leaderboardRows.add(temp2);
        leaderboardRows.add(temp3);
        leaderboardRows.add(temp4);
        leaderboardRows.add(temp5);
        lb.setRows(leaderboardRows);
        String st1 = lb.toString();
        assertNotEquals(st, st1);
    }

    // Test for player movement following player input

    @Test
    public void checkMovementDir() {
        MoveBall moveBall = new MoveBall();
        int[] hpChar1 = new int[]{100, 1};
        Player player = Player.getPlayer(null, 1000, 1000, moveBall, "",
                new SpriteSheet(null), hpChar1);

        moveBall.setController(110, 100);
        assertTrue(moveBall.getControllerX() > 0);
        assertEquals(0, moveBall.getControllerY(), 0.0);

        moveBall.setController(90, 100);
        assertTrue(moveBall.getControllerX() < 0);
        assertEquals(0, moveBall.getControllerY(), 0.0);

        moveBall.setController(100, 110);
        assertEquals(0, moveBall.getControllerX(), 0.0);
        assertTrue(moveBall.getControllerY() > 0);

        moveBall.setController(100, 90);
        assertEquals(0, moveBall.getControllerX(), 0.0);
        assertTrue(moveBall.getControllerY() < 0);
    }
    

    private ArrayList<LeaderboardRow> makeTestRows() {
        ArrayList<LeaderboardRow> leaderboardRows = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            leaderboardRows.add(new LeaderboardRow("Bob" + i, i * 2, "Today"));
        }
        return leaderboardRows;
    }
}
