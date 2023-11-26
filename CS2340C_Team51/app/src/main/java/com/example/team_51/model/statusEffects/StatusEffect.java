package com.example.team_51.model.statusEffects;

import android.graphics.Canvas;

import com.example.team_51.model.map.Tilemap;
import com.example.team_51.viewmodels.GameDisplay;

public interface StatusEffect {
    public double addSE();
    public void draw(Canvas canvas, GameDisplay gameDisplay);
    public void update(Tilemap tilemap);
}
