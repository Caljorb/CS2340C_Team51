package com.example.team_51.model;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.cs2340c_team51.R;
import com.example.team_51.viewmodels.GameDisplay;

public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    public static final int MAX_HEALTH_POINTS = 5;
    private double posX;
    private int hp;
    private double posY;
    private Sprite sprite;
    private static Player player;

    private Player(Context context, double posX, double posY, double radius, int hp) {
        super(context, ContextCompat.getColor(context, R.color.player),
                posX, posY, radius);
        this.posX = posX;
        this.posY = posY;
        this.hp = hp;

    }

    public static synchronized Player getPlayer(Context context, double posX, double posY,
                                                double radius, int hp) { // implement singleton
        if (player == null) {
            player = new Player(context, posX, posY, radius, hp);
        }
        return player;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        super.draw(canvas, gameDisplay);
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
    public void update() {

    }

}
