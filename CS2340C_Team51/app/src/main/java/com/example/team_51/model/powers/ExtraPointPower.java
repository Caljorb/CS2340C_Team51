package com.example.team_51.model.powers;

import android.graphics.Canvas;

import com.example.team_51.viewmodels.GameDisplay;
import com.example.team_51.viewmodels.SpriteSheet;

public class ExtraPointPower extends PowerDecorator {
    public ExtraPointPower(PowerUp powerUp, SpriteSheet spriteSheet) {
        super(powerUp);
        super.sprite = spriteSheet.getPowerSprite(1);
    }

    @Override
    public int addPower() {
        return addPointPower();
    }

    private int addPointPower() {
        return 10000;
    }
}
