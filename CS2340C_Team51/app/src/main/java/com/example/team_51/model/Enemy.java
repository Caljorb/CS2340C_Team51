package com.example.team_51.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.team_51.model.map.Tilemap;
import com.example.team_51.viewmodels.GameDisplay;
import com.example.team_51.viewmodels.SpriteSheet;

public class Enemy extends Circle implements MovementStrategy, MoveSubscriber {

    private double posX;
    private double posY;
    private int hp;
    private Sprite sprite;

    public Enemy(Context context, double posX, double posY, SpriteSheet spriteSheet,
                 int hp, int enemy) {
        super(Color.WHITE, posX, posY, 32);
        this.posX = posX;
        this.posY = posY;
        this.hp = hp;
        this.sprite = spriteSheet.getEnemySprite(enemy);
        // enemy: [1, 4]
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(24f);
        canvas.drawText("" + hp, (int) posX, (int) (posY - 20), paint);
        // check hp is put at good spot
        sprite.draw(canvas, (int) gameDisplay.gameToDisplayCoordinatesX(posX),
                (int) gameDisplay.gameToDisplayCoordinatesY(posY));
    }

    @Override
    public void update() {

    }

    @Override
    public void update(MoveBall moveBall, Tilemap tilemap) {

    }

    @Override
    public void move(MoveBall moveBall, Tilemap tilemap) {

    }

    @Override
    public boolean checkOutOfBounds(double posX, double posY) {
        return false;
    }
}
