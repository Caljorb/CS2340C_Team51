package com.example.team_51.model.powers;

import com.example.team_51.viewmodels.SpriteSheet;

public class SpeedPower extends PowerDecorator {
    public SpeedPower(PowerUp powerUp, SpriteSheet spriteSheet) {
        super(powerUp);
        super.sprite = spriteSheet.getPowerSprite(2);
    }

    @Override
    public int addPower() {
        ((PowerUpInstance) super.powerUp).setPosX(100000);
        ((PowerUpInstance) super.powerUp).setPosY(100000);
        return addSpeedPower();
    }

    private int addSpeedPower() {
        return 2;
    }
}
