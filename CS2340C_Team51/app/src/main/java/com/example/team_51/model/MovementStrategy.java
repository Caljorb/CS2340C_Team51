package com.example.team_51.model;

import com.example.team_51.model.map.Tilemap;

public interface MovementStrategy {
    abstract void move(MoveBall moveBall, Tilemap tilemap);

    abstract boolean checkOutOfBounds(double posX, double posY);
}
