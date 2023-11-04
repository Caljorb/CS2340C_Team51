package com.example.team_51.model.enemies;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.team_51.model.Sprite;
import com.example.team_51.viewmodels.GameDisplay;
import com.example.team_51.viewmodels.SpriteSheet;

public class Snake implements Enemy {
    private double posX;
    private double posY;
    private Sprite sprite;
    private int hp;

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
}
