package com.example.team_51.model.enemies;

import android.graphics.Canvas;

import com.example.team_51.viewmodels.GameDisplay;

public interface Enemy {
    void spawn(int map, int count);
    void draw(Canvas canvas, GameDisplay gameDisplay);
}
