package com.example.team_51.model.enemies;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.team_51.model.Game;
import com.example.team_51.model.MoveStratEnemy;
import com.example.team_51.model.Player;
import com.example.team_51.model.Sprite;
import com.example.team_51.model.map.Tilemap;
import com.example.team_51.viewmodels.GameDisplay;
import com.example.team_51.viewmodels.GameLoop;
import com.example.team_51.viewmodels.SpriteSheet;

public class Rat implements Enemy, MoveStratEnemy {
    public static final double SPEED_PIXELS_PER_SECOND = 150.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private double posX;
    private double posY;
    private Sprite sprite;
    private int hp;
    private double veloX;
    private double veloY;

    Rat(SpriteSheet spriteSheet) {
        this.hp = 25;
        this.sprite = spriteSheet.getEnemySprite(4);
    }

    @Override
    public void spawn(int map, int count) {
        switch (map) {
        case 1:
            posX = 2400;
            posY = 1200;
            break;
        case 2:
            if (count < 1) {
                posX = 2300;
                posY = 900;
            } else {
                posX = 2750;
                posY = 670;
            }
            break;
        default:
            break;
        }
        System.out.println("PosX: " + posX + ", PosY: " + posY);
    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(36f);
        canvas.drawText("" + hp, (int) gameDisplay.gameToDisplayCoordinatesX(posX + 5),
                (int) gameDisplay.gameToDisplayCoordinatesY(posY), paint);
        sprite.draw(canvas, (int) gameDisplay.gameToDisplayCoordinatesX(posX),
                (int) gameDisplay.gameToDisplayCoordinatesY(posY));
    }

    @Override
    public void update(Tilemap tilemap, int updates) {
        if (!isWall(tilemap, posX, posY)) {
            int currDir = updates / 10;
            if (updates % 20 == 0) {
                switch (currDir % 4) { // move in line
                case 2:
                    veloX = 2 * MAX_SPEED; // right
                    break;
                default:
                    veloX = -2 * MAX_SPEED; // left
                    break;
                }
            }
        } else { // bounce off wall on collision
            veloX = veloX * -1;
            veloY = veloY * -1;
        }

        // move straight
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

    @Override
    public boolean isWall(Tilemap tilemap, double posX, double posY) {
        int[][] walls = tilemap.getWalls();

        double tileX = 1117.4; // where tile 1 starts X
        double tileY = 500.6; // where tile 1 starts Y

        int c = (int) ((posX - tileX + 32) / 64.0); // find tile index based on enemy pos
        int r = (int) ((posY - tileY + 32) / 64.0);

        try {
            if (walls[r][c] == 3 || walls[r][c] == 4) {
                System.out.println("C: " + c + ", R: " + r);
                System.out.println("True");
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            return true; // enemy position can be weird sometimes
        }

        return false; // enemy was not in any walls
    }
    public double getPosX() {
        return posX;
    }
    public double getPosY() {
        return posY;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getHp() {
        return hp;
    }
    public void observerUpdate(Game game) {
        Player player = game.getPlayer();
        player.setHp(player.getHp() - 15);
        this.setHp(this.getHp() - 2);
    }
}
