package com.example.team_51.model;

import android.graphics.Canvas;

import com.example.team_51.viewmodels.GameDisplay;

public abstract class GameObject {
    protected double positionX, positionY = 0.0;
    protected double velocityX, velocityY = 0.0;
    protected double directionX = 1.0;
    protected double directionY = 0.0;

    public GameObject() { }

    public GameObject(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public double getPositionX() { return positionX; }
    public double getPositionY() { return positionY; }

    public double getDirectionX() { return directionX; }
    public double getDirectionY() { return directionY; }

    public abstract void draw(Canvas canvas, GameDisplay gameDisplay);
    public abstract void update();

}
