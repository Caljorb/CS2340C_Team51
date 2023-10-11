package com.example.team_51.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.cs2340c_team51.R;
import com.example.team_51.viewmodels.GameDisplay;
import com.example.team_51.viewmodels.SpriteSheet;

public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    public static final int MAX_HEALTH_POINTS = 5;
    private double posX;
    private int hp;
    private String name;
    private double posY;
    private Sprite sprite;
    private int[] hpChar;
    private static Player player;


    private Player(Context context, double posX, double posY, double radius, String name,
                   SpriteSheet spriteSheet, int[] hpChar) {
        super(Color.WHITE, posX, posY, radius);
        this.posX = posX;
        this.posY = posY;
        this.hpChar = new int[]{hpChar[0], hpChar[1]};
        this.hp = hpChar[0];
        this.name = name;
        this.sprite = spriteSheet.getPlayerSprite(hpChar[1]);
    }

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
    public double getPlayerPosX() {
        return posX;
    }
    public double getPlayerPosY() {
        return posY;
    }
    public int getHp() {
        return hp;
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

    public void update() {

    }

}
