package com.example.team_51;

import android.content.Context;
import android.graphics.Canvas;

public class Player {
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    public static final int MAX_HEALTH_POINTS = 5;
    private double posX;
    private int hp;
    public static int maxHp;
    private double posY;
    private Sprite sprite;
    private HealthBar healthBar;

    public Player(Context context, double posX, double posY, String name) {
        int maxHp;
        this.posX = posX;
        this.posY = posY;

        if (name.equals("Marcus")) {
            maxHp = 120;
        } else if (name.equals("Ariel")) {
            maxHp = 75;
        } else {
            maxHp = 100;
        }
        this.healthBar = new HealthBar(context,this);
    }

    public void draw(Canvas canvas) {
        sprite.draw(canvas);
        healthBar.draw(canvas);
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
