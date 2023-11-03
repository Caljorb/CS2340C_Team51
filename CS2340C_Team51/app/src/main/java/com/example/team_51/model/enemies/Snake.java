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
        case 2:
            posX = 2800;
            posY = 1600;
            break;
        case 3:
            if (count < 1) {
                posX = 1400;
                posY = 1200;
            } else {
                posX = 3000;
                posY = 1000;
            }
            break;
        default:
            break;
        }
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
