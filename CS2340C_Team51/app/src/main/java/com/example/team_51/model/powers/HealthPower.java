package com.example.team_51.model.powers;

import android.graphics.Canvas;

import com.example.team_51.viewmodels.GameDisplay;
import com.example.team_51.viewmodels.SpriteSheet;

public class HealthPower extends PowerDecorator {
    public HealthPower(PowerUp powerUp, SpriteSheet spriteSheet) {
        super(powerUp);
        super.sprite = spriteSheet.getPowerSprite(0);
    }

    @Override
    public int addPower() {
        return addHealthPower();
    }

    private int addHealthPower() {
        return 25;
    }
}
