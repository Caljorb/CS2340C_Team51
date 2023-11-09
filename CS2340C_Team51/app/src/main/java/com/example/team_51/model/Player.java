package com.example.team_51.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.team_51.model.map.Tilemap;
import com.example.team_51.viewmodels.GameDisplay;
import com.example.team_51.viewmodels.GameLoop;
import com.example.team_51.viewmodels.SpriteSheet;

public class Player extends Circle implements MovementStrategy, MoveSubscriber {
    public static final double SPEED_PIXELS_PER_SECOND = 300.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    public static final int MAX_HEALTH_POINTS = 5;
    private double posX;
    private int hp;
    private String name;
    private double posY;
    private Sprite sprite;
    private int[] hpChar;
    private static Player player;
    private double veloX;
    private double veloY;
    private MoveBall moveBall;


    private Player(Context context, double posX, double posY, MoveBall moveBall, String name,
                   SpriteSheet spriteSheet, int[] hpChar) {
        super(Color.WHITE, posX, posY, 32);
        this.posX = posX;
        this.posY = posY;
        this.moveBall = moveBall;
        this.hpChar = new int[]{hpChar[0], hpChar[1]};
        this.hp = hpChar[0];
        this.name = name;
        this.sprite = spriteSheet.getPlayerSprite(hpChar[1]);
    }

    private Player(Context context, double posX, double posY, double radius, String name,
                   SpriteSheet spriteSheet, int[] hpChar) {
        super(Color.WHITE, posX, posY, 32);
        this.posX = posX;
        this.posY = posY;
        this.hpChar = new int[]{hpChar[0], hpChar[1]};
        this.hp = hpChar[0];
        this.name = name;
        this.sprite = spriteSheet.getPlayerSprite(hpChar[1]);
    }

    public static synchronized Player getPlayer(Context context, double posX, double posY,
                                                MoveBall moveBall, String name,
                                                SpriteSheet spriteSheet, int[] hpChar) {
        if (player == null) {
            player = new Player(context, posX, posY, moveBall, name, spriteSheet, hpChar);
        } else {
            player.setPlayer(moveBall, name, spriteSheet, hpChar);
        }
        return player;
    } // singleton to limit to a single instance

    public static synchronized Player getPlayer(Context context, double posX, double posY,
                                                double radius, String name,
                                                SpriteSheet spriteSheet, int[] hpChar) {
        if (player == null) {
            player = new Player(context, posX, posY, radius, name, spriteSheet, hpChar);
        } else {
            player.setPlayer(name, spriteSheet, hpChar);
        }
        return player;
    } // singleton to limit to a single instance

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(48f);
        canvas.drawText("Name: " + name, 80, 100, paint);
        canvas.drawText("Health: " + hp, 80, 150, paint);
        sprite.draw(canvas, (int) gameDisplay.gameToDisplayCoordinatesX(posX),
                (int) gameDisplay.gameToDisplayCoordinatesY(posY));
    }

    @Override
    public void update() {
        // bruh
    }

    public void update(Tilemap tilemap) {
        update(moveBall, tilemap);
    }



    public double getPlayerPosX() {
        return posX;
    }
    public double getPlayerPosY() {
        return posY;
    }
    public int getHp() {
        return hp;
    }

    private void setPlayer(MoveBall moveBall, String name, SpriteSheet spriteSheet, int[] hpChar) {
        this.moveBall = moveBall;
        this.hpChar = new int[]{hpChar[0], hpChar[1]};
        this.hp = hpChar[0];
        this.name = name;
        this.sprite = spriteSheet.getPlayerSprite(hpChar[1]);
    }

    private void setPlayer(String name, SpriteSheet spriteSheet, int[] hpChar) {
        this.hpChar = new int[]{hpChar[0], hpChar[1]};
        this.hp = hpChar[0];
        this.name = name;
        this.sprite = spriteSheet.getPlayerSprite(hpChar[1]);
    }

    public String getName() {
        return name;
    }

    public boolean checkName(String nameEntry) {
        boolean nameSel = false;
        if (nameEntry != null) {
            // cannot pick null/empty/whitespace strings
            String temp = nameEntry;
            if (temp.trim().length() > 0 && !temp.contains(" ")) {
                nameSel = true;
            }
        }
        return nameSel;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void move(MoveBall moveBall, Tilemap tilemap) {
        veloX = moveBall.getControllerX() * MAX_SPEED; // moveBall.getController is always 0
        veloY = moveBall.getControllerY() * MAX_SPEED;

        //System.out.println(veloX);
        //System.out.println(veloY);

        double tempX = posX + veloX;
        double tempY = posY + veloY;

        if (isWall(tilemap, tempX, tempY)) {
            tempX = posX; // dont let move if there is a wall
            tempY = posY;
        }

        if (!checkOutOfBounds(tempX, tempY)) {
            posX = tempX;
            posY = tempY;
        }
    }

    public void move(MoveBall moveBall) {
        veloX = moveBall.getControllerX() * MAX_SPEED; // moveBall.getController is always 0
        veloY = moveBall.getControllerY() * MAX_SPEED;

        //System.out.println(veloX);
        //System.out.println(veloY);

        double tempX = posX + veloX;
        double tempY = posY + veloY;

        if (!checkOutOfBounds(tempX, tempY)) {
            posX = tempX;
            posY = tempY;
        }
    }

    @Override
    public boolean checkOutOfBounds(double posX, double posY) {
        boolean xIn = posX > 1110 && posX < 3300;
        boolean yIn = posY > 500 && posY < 1400;

        return !(xIn && yIn);
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    @Override
    public void update(MoveBall moveBall, Tilemap tilemap) {
        move(moveBall, tilemap);
    }

    public boolean isWall(Tilemap tilemap, double posX, double posY) {
        int[][] walls = tilemap.getWalls();

        /*for (int[] w : walls) {
            System.out.println(Arrays.toString(w));
        }*/

        double tileX = 1117.4; // where tile 1 starts X
        double tileY = 500.6; // where tile 1 starts Y

        int c = (int) ((posX - tileX + 32) / 64.0); // find tile index based on player pos
        int r = (int) ((posY - tileY + 32) / 64.0);

        if (walls[r][c] == 3 || walls[r][c] == 4) {
            System.out.println("C: " + c + ", R: " + r);
            System.out.println("True");
            return true;
        }

        return false; // player was not in any walls
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
}
