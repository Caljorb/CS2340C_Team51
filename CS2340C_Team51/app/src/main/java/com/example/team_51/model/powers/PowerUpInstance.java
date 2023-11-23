package com.example.team_51.model.powers;


import android.graphics.Canvas;

import com.example.team_51.viewmodels.GameDisplay;

public class PowerUpInstance implements PowerUp {
    private double posX;
    private double posY;
    public PowerUpInstance() {
        this.posX = 2000;
        this.posY = 800;
    }

    @Override
    public int addPower() {
        return 1;
    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {

    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }
}
