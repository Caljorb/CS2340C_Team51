package com.example.team_51.model.enemies;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.team_51.model.Sprite;
import com.example.team_51.viewmodels.GameDisplay;
import com.example.team_51.viewmodels.SpriteSheet;

public class Bat implements Enemy {
    private double posX;
    private double posY;
    private Sprite sprite;
    private int hp;

    Bat(SpriteSheet spriteSheet) {
        this.hp = 15;
        this.sprite = spriteSheet.getEnemySprite(1);
        // initializes traits of bat
    }

    @Override
    public void spawn(int map, int count) {
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
            posY = 1500;
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
