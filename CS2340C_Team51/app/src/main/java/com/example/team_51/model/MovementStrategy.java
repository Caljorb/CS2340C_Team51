package com.example.team_51.model;

public interface MovementStrategy {
    abstract boolean move();

    abstract boolean checkOutOfBounds(double posX, double posY);
}
