package com.example.team_51.model.statusEffects;

import android.graphics.Canvas;

import com.example.team_51.model.Sprite;
import com.example.team_51.model.map.Tilemap;
import com.example.team_51.viewmodels.GameDisplay;

//This is the StatusDecorator Class
public class StatusDecorator implements StatusEffect {
    protected StatusEffect se;
    protected Sprite sprite;

    public StatusDecorator(StatusEffect se) {
        this.se = se;
    }

    @Override
    public double addSE() {
        return se.addSE();
    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        sprite.draw(canvas,
                (int) gameDisplay.gameToDisplayCoordinatesX(((StatusInstance) se).getPosX()),
                (int) gameDisplay.gameToDisplayCoordinatesY(((StatusInstance) se).getPosY()));
    }

    @Override
    public void update(Tilemap tilemap) {
        ((StatusInstance) se).setPos(tilemap);
    }

    public double getPosX() {
        return ((StatusInstance) se).getPosX();
    }

    public double getPosY() {
        return ((StatusInstance) se).getPosY();
    }
}
