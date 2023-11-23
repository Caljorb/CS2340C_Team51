package com.example.team_51.model.powers;

import android.graphics.Canvas;

import com.example.team_51.viewmodels.GameDisplay;

public interface PowerUp {
    public int addPower();
    public void draw(Canvas canvas, GameDisplay gameDisplay);
}
