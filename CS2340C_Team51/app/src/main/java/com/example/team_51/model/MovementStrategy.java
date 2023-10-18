package com.example.team_51.model;

public interface MovementStrategy {
    abstract void move(MoveBall moveBall);

    abstract boolean checkOutOfBounds(double posX, double posY);
}
