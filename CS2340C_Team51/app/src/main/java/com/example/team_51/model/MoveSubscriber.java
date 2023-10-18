package com.example.team_51.model;

import com.example.team_51.model.map.Tilemap;

public interface MoveSubscriber {
    abstract void update(MoveBall moveBall, Tilemap tilemap);
    abstract boolean isWall(Tilemap tilemap);
}
