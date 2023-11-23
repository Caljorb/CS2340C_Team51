package com.example.team_51.model.powers;

import android.graphics.Canvas;

import com.example.team_51.model.Sprite;
import com.example.team_51.model.map.Tilemap;
import com.example.team_51.viewmodels.GameDisplay;

public abstract class PowerDecorator implements PowerUp {
    protected PowerUp powerUp;
    protected Sprite sprite;

    public PowerDecorator(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    @Override
    public int addPower() {
        return powerUp.addPower();
    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        sprite.draw(canvas,
                (int) gameDisplay.gameToDisplayCoordinatesX(((PowerUpInstance) powerUp).getPosX()),
                (int) gameDisplay.gameToDisplayCoordinatesY(((PowerUpInstance) powerUp).getPosY()));
    }

    @Override
    public void update(Tilemap tilemap) {
        ((PowerUpInstance) powerUp).setPos(tilemap);
    }

    public double getPosX() {
        return ((PowerUpInstance) powerUp).getPosX();
    }

    public double getPosY() {
        return ((PowerUpInstance) powerUp).getPosY();
    }
}
