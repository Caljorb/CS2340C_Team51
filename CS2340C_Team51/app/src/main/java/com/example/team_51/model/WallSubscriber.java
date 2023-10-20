package com.example.team_51.model;

import com.example.team_51.model.map.Tilemap;

public interface WallSubscriber {
    abstract boolean isWall(Tilemap tilemap);
}
