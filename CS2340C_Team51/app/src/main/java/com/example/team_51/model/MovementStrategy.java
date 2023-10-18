package com.example.team_51.model;

public interface MovementStrategy {
    abstract void move();

    abstract boolean checkOutOfBounds(double posX, double posY);
}
