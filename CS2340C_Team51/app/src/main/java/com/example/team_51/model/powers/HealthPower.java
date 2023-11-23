package com.example.team_51.model.powers;

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
