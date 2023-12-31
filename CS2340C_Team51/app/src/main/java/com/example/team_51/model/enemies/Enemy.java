package com.example.team_51.model.enemies;

import android.graphics.Canvas;

import com.example.team_51.model.Game;
import com.example.team_51.model.map.Tilemap;
import com.example.team_51.viewmodels.GameDisplay;

public interface Enemy {
    void spawn(int map, int count);
    void draw(Canvas canvas, GameDisplay gameDisplay);
    void update(Tilemap tilemap, int updates);
    double getPosX();
    double getPosY();
    void observerUpdate(Game game, int damage);
    void setHp(int hp);
    int getHp();

}
