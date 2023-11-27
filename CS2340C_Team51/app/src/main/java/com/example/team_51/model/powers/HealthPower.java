package com.example.team_51.model.powers;

import com.example.team_51.viewmodels.SpriteSheet;

public class HealthPower extends PowerDecorator {
    public HealthPower(PowerUp powerUp, SpriteSheet spriteSheet) {
        super(powerUp);
        super.sprite = spriteSheet.getPowerSprite(0);
    }

    @Override
    public int addPower() {
        ((PowerUpInstance) super.powerUp).setPosX(100000);
        ((PowerUpInstance) super.powerUp).setPosY(100000);
        return addHealthPower();
    }

    private int addHealthPower() {
        return 25;
    }
}
