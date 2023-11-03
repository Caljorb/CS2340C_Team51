package com.example.team_51.model.enemies;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.team_51.model.Sprite;
import com.example.team_51.viewmodels.GameDisplay;
import com.example.team_51.viewmodels.SpriteSheet;

public class Slime implements Enemy {
    private double posX;
    private double posY;
    private Sprite sprite;
    private int hp;

    Slime(SpriteSheet spriteSheet) {
        this.hp = 10;
        this.sprite = spriteSheet.getEnemySprite(2);
    }
    @Override
    public void spawn(int map, int count) {
        /*switch (map) {
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
            if (count < 1) {
                posX = 2400;
                posY = 1200;
            } else {
                posX = 1800;
                posY = 1000;
            }
            break;
        case 2:
            if (count < 1) {
                posX = 2400;
                posY = 1200;
            } else {
                posX = 1800;
                posY = 1000;
            }
            break;
        default:
            if (count < 1) {
                posX = 2400;
                posY = 1200;
            } else {
                posX = 1800;
                posY = 1000;
            }
            break;
        }*/
        posX = 2000;
        posY = 1000;
    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(36f);
        canvas.drawText("Health: " + hp, (int) posX, (int) posY - 20, paint);
        // WHY DOESNT TEXT APPEAR
        sprite.draw(canvas, (int) gameDisplay.gameToDisplayCoordinatesX(posX),
                (int) gameDisplay.gameToDisplayCoordinatesY(posY));
    }


}
