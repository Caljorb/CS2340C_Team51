package com.example.team_51.model.enemies;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.team_51.model.Sprite;
import com.example.team_51.model.map.Tilemap;
import com.example.team_51.viewmodels.GameDisplay;
import com.example.team_51.viewmodels.GameLoop;
import com.example.team_51.viewmodels.SpriteSheet;

public class Bat implements Enemy {
    public static final double SPEED_PIXELS_PER_SECOND = 150.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private double posX;
    private double posY;
    private Sprite sprite;
    private int hp;
    private double veloX;
    private double veloY;

    Bat(SpriteSheet spriteSheet) {
        this.hp = 15;
        this.sprite = spriteSheet.getEnemySprite(1);
        // initializes traits of bat
    }

    @Override
    public void spawn(int map, int count) {
        System.out.println(map);
        switch (map) {
        case 0:
            if (count < 1) {
                posX = 2800;
                posY = 800;
            } else {
                posX = 2000;
                posY = 650;
            }
            break;
        case 1:
            posX = 1500;
            posY = 850;
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
        int currDir = updates / 10;
        if (updates % 27 == 0) { // move erratically
            switch (currDir % 5) {
            case 1:
                veloX = 1 * MAX_SPEED; // right
                break;
            case 2:
                veloY = -1 * MAX_SPEED; // up
                break;
            case 3:
                veloX = -1 * MAX_SPEED; // left
                break;
            default:
                veloY = 1 * MAX_SPEED; // down
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
