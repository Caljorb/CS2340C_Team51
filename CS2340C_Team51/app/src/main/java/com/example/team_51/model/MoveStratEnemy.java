package com.example.team_51.model;

import com.example.team_51.model.map.Tilemap;

public interface MoveStratEnemy {
    boolean checkOutOfBounds(double posX, double posY);
    boolean isWall(Tilemap tilemap, double posX, double posY);
}
