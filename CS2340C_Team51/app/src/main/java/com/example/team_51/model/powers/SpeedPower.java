package com.example.team_51.model.powers;

import com.example.team_51.viewmodels.SpriteSheet;

public class SpeedPower extends PowerDecorator {
    //private Sprite sprite;
    public SpeedPower(PowerUp powerUp, SpriteSheet spriteSheet) {
        super(powerUp);
        super.sprite = spriteSheet.getPowerSprite(2);
    }

    @Override
    public int addPower() {
        return addSpeedPower();
    }

    private int addSpeedPower() {
        return 2;
    }
}
