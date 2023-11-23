package com.example.team_51.model.powers;

import android.graphics.Canvas;

import com.example.team_51.viewmodels.GameDisplay;

public class HealthPower extends PowerDecorator {
    public HealthPower(PowerUp powerUp) {
        super(powerUp);
    }

    @Override
    public int addPower() {
        return addHealthPower();
    }

    private int addHealthPower() {
        return 25;
    }
}
