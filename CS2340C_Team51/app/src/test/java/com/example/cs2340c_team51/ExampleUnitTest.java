package com.example.cs2340c_team51;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.example.team_51.model.Game;
import com.example.team_51.model.LeaderboardRow;
import com.example.team_51.model.MoveBall;
import com.example.team_51.model.Player;
import com.example.team_51.model.enemies.Bat;
import com.example.team_51.model.enemies.BatFactory;
import com.example.team_51.model.enemies.Enemy;
import com.example.team_51.model.enemies.EnemyFactory;
import com.example.team_51.model.enemies.Rat;
import com.example.team_51.model.enemies.RatFactory;
import com.example.team_51.model.enemies.Slime;
import com.example.team_51.model.enemies.SlimeFactory;
import com.example.team_51.model.enemies.Snake;
import com.example.team_51.model.enemies.SnakeFactory;
import com.example.team_51.model.map.Tilemap;
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


    // Test you cant move out of bounds
    @Test
    public void checkOutOfBounds() {
        int[] hpChar1 = new int[]{100, 1};
        MoveBall moveBall = new MoveBall();
        Player player = Player.getPlayer(null, 1000, 1000, moveBall, "",
                new SpriteSheet(null), hpChar1);
        // put player before x coords
        player.checkOutOfBounds(1109, 501);

        // put player after x coords
        player.checkOutOfBounds(3301, 501);

        // put player before y coords
        assertTrue(player.checkOutOfBounds(1111, 499));

        // put player after y coords
        assertTrue(player.checkOutOfBounds(1111, 1401));
    }

    // Test player moves to next map when they reach the exit (Rashmith)
    @Test
    public void checkMoveMaps() {
        int[] hpChar1 = new int[]{100,1};
        MoveBall moveBall = new MoveBall();
        Player player = Player.getPlayer(null, 1000, 1000, moveBall, "",
                                        new SpriteSheet(null), hpChar1);
        Tilemap tilemap = new Tilemap(0, player);
        player.setPosX(3237);
        player.setPosY(1200);
        tilemap.updateTest();
        assertEquals(1, tilemap.getMap());
    }

    // Test after last exit player moves to win screen (Rashmith)
    @Test
    public void checkChangeScreens() {
        int[] hpChar1 = new int[]{100,1};
        MoveBall moveBall = new MoveBall();
        Player player = Player.getPlayer(null, 1000, 1000, moveBall, "",
                                        new SpriteSheet(null), hpChar1);
        Tilemap tilemap = new Tilemap(2, player);
        player.setPosX(3237);
        player.setPosY(1200);
        assertTrue(tilemap.updateTest());
        
    }

    // Test player respawns at center after a retry
    @Test
    public void checkRespawn() {
        MoveBall moveball = new MoveBall();
        int[] hpChar = new int[] {100, 1};
        Player player = Player.getPlayer(null, 1000, 1000, moveball, "dlee",
                new SpriteSheet(null), hpChar);
        Tilemap tilemap = new Tilemap(2, player);

        player.setPosX(3237);
        player.setPosY(1200);
        tilemap.updateTest();

        assertEquals(2240, player.getPlayerPosX(), 0.0);
        assertEquals(1024, player.getPlayerPosY(), 0.0);

    }

    // Test if tile is 3 or 4 it is a wall - Daniel
    @Test
    public void checkIfWall() {
        MoveBall moveball = new MoveBall();
        int[] hpChar = new int[] {100, 1};
        Player player = Player.getPlayer(null, 1000, 1000, moveball, "dlee",
                new SpriteSheet(null), hpChar);
        Tilemap tilemap = new Tilemap(2, player);

        assertTrue(player.isWall(tilemap, 1110, 500));
        assertFalse(player.isWall(tilemap, 2240, 1024));
    }

    // Test player position is set to very left of screen after map change

    // Test player cannot move back after moving to a new map

    private ArrayList<LeaderboardRow> makeTestRows() {
        ArrayList<LeaderboardRow> leaderboardRows = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            leaderboardRows.add(new LeaderboardRow("Bob" + i, i * 2, "Today"));
        }
        return leaderboardRows;
    }

    // A player spawns at the beginning of the screen after exiting (Kavya)

    @Test
    public void checkLeftSideChange() {
        int[] hpChar1 = new int[]{100, 1};
        MoveBall moveBall = new MoveBall();
        Player player = Player.getPlayer(null, 1000, 1000, moveBall, "",
                new SpriteSheet(null), hpChar1);
        Tilemap tilemap = new Tilemap(0, player);

        player.setPosX(3237);
        player.setPosY(1200);
        tilemap.updateTest();
        assertEquals(1125, player.getPlayerPosX(), 0.0);
    }

    // A player cannot move back after moving to a new map (Kavya)

    @Test
    public void checkNoMoveBack() {
        MoveBall moveball = new MoveBall();
        int[] hpChar = new int[] {100, 1};
        Player player = Player.getPlayer(null, 1000, 1000, moveball, "",
                new SpriteSheet(null), hpChar);
        Tilemap tilemap = new Tilemap(0, player);

        player.setPosX(3237);
        player.setPosY(1200);
        tilemap.updateTest();
        player.setPosX(player.getPlayerPosX() - 32);
        assertEquals(1, tilemap.getMap());
    }

    // check enemy cant move oob (Caleb)

    @Test
    public void checkEnemyOoB() {
        EnemyFactory batFactory = new BatFactory();
        Enemy bat = batFactory.create(0, new SpriteSheet(null));
        Bat e = (Bat) bat;
        assertTrue(e.checkOutOfBounds(3000000, 0));
        assertTrue(e.checkOutOfBounds(220, -1111));
        assertFalse(e.checkOutOfBounds(1200, 800));
    }

    // check enemy collides with walls (Caleb)

    @Test
    public void checkEnemyWall() {
        EnemyFactory slimeFactory = new SlimeFactory();
        Enemy slime = slimeFactory.create(0, new SpriteSheet(null));
        Slime e = (Slime) slime;

        Tilemap tilemap = new Tilemap(0, null);
        assertTrue(e.isWall(tilemap, 1110, 500));
        assertFalse(e.isWall(tilemap, 2240, 1024));
    }
    
    private boolean checkCollision(Enemy enemy, Player player) {
            double enemyPosX = enemy.getPosX();
            double enemyPosY = enemy.getPosY();
            double playerPosX = player.getPlayerPosX();
            double playerPosY = player.getPlayerPosY();

            if ((Math.abs(enemyPosX - playerPosX) <= 32)
                    && (Math.abs(enemyPosY - playerPosY) <= 32)) {
                player.setHp(player.getHp() - 10);
                if (player.getHp() <= 0) {
                    return true;
                }
            }
        return false;
    }
    
    // check player can be hit by enemy (Kavya)


    @Test
    public void checkEnemyPlayerCollision() {
        EnemyFactory slimeFactory = new SlimeFactory();
        Enemy slime = slimeFactory.create(0, new SpriteSheet(null));
        Slime e = (Slime) slime;


        int[] hpChar = new int[]{100, 1};
        MoveBall moveBall = new MoveBall();
        Player player = Player.getPlayer(null, 2400, 1200, moveBall, "",
                new SpriteSheet(null), hpChar);
        Tilemap tilemap = new Tilemap(0, player);


        assertTrue(player.getPlayerPosX() == e.getPosX()
                && player.getPlayerPosY() == e.getPosY());
        player.setPosX(1000);
        assertFalse(player.getPlayerPosX() == e.getPosX()
                && player.getPlayerPosY() == e.getPosY());
    }

    // check enemy deals damage to player (Kavya)


    @Test
    public void checkEnemyHurts() {
        EnemyFactory slimeFactory = new SlimeFactory();
        Enemy slime = slimeFactory.create(0, new SpriteSheet(null));
        Slime e = (Slime) slime;


        int[] hpChar = new int[]{100, 1};
        MoveBall moveBall = new MoveBall();
        Player player = Player.getPlayer(null, 2400, 1200, moveBall, "",
                new SpriteSheet(null), hpChar);
        Tilemap tilemap = new Tilemap(0, player);


        assertEquals(100, player.getHp());

        checkCollision(e, player);


        assertEquals(90, player.getHp());
    }

}
