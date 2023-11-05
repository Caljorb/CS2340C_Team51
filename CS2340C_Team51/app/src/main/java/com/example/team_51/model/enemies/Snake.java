package com.example.team_51.model.enemies;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.team_51.model.Sprite;
import com.example.team_51.model.map.Tilemap;
import com.example.team_51.viewmodels.GameDisplay;
import com.example.team_51.viewmodels.GameLoop;
import com.example.team_51.viewmodels.SpriteSheet;

public class Snake implements Enemy {
    public static final double SPEED_PIXELS_PER_SECOND = 100.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private double posX;
    private double posY;
    private Sprite sprite;
    private int hp;
    private double veloX;
    private double veloY;

    Snake(SpriteSheet spriteSheet) {
        this.hp = 20;
        this.sprite = spriteSheet.getEnemySprite(3);
    }
    @Override
    public void spawn(int map, int count) {
        switch (map) {
        case 1:
            posX = 2000;
            posY = 650;
            break;
        case 2:
            if (count < 1) {
                posX = 1600;
                posY = 700;
            } else {
                posX = 3000;
                posY = 1000;
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
        int currDir = updates / 10; // disregards last digit of updates
        if (updates % 18 == 0) { // honestly have no idea why 18 makes a "slither" pattern
            switch (currDir % 4) { // but its cool so i like it
            case 1:
                veloX = 1 * MAX_SPEED; // right
                break;
            case 2:
                veloY = 1 * MAX_SPEED; // down
                break;
            case 3:
                veloX = -1 * MAX_SPEED; // left
                break;
            default:
                veloY = -1 * MAX_SPEED; // up
                break;
            }
        }
        // move straight
        double tempX = posX + veloX;
        double tempY = posY + veloY;

        posX = tempX;
        posY = tempY;
    }

    @Override
    public void move(int updates) {

    }
}
