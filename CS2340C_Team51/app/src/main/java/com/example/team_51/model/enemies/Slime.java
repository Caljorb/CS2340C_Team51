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

public class Slime implements Enemy, MoveStratEnemy {
    public static final double SPEED_PIXELS_PER_SECOND = 50.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private double posX;
    private double posY;
    private Sprite sprite;
    private int hp;
    private double veloX;
    private double veloY;

    Slime(SpriteSheet spriteSheet) {
        this.hp = 10;
        this.sprite = spriteSheet.getEnemySprite(2);
    }
    @Override
    public void spawn(int map, int count) {
        switch (map) {
        case 0:
            if (count < 1) {
                posX = 2400;
                posY = 1200;
            } else {
                posX = 1800;
                posY = 1000;
            }
            break;
        case 1:
            posX = 2600;
            posY = 900;
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
            if (updates % 110 == 0) {
                // turn right from previous direction
                switch (currDir % 4) {
                case 1:
                    veloX = -1 * MAX_SPEED; // left
                    veloY = 0;
                    break;
                case 2:
                    veloY = 1 * MAX_SPEED; // down
                    veloX = 0;
                    break;
                case 3:
                    veloX = 1 * MAX_SPEED; // right
                    veloY = 0;
                    break;
                default:
                    veloY = -1 * MAX_SPEED; // up
                    veloX = 0;
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
        player.setHp(player.getHp() - 7);
        this.setHp(this.getHp() - 2);
    }
}
