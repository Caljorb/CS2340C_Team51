package com.example.team_51.model.powers;

public class HealthPower extends PowerDecorator {
    public HealthPower(PowerUp powerUp) {
        super(powerUp);
    }

    @Override
    public void addPower() {
        addHealthPower();
    }

    private void addHealthPower() {

    }
}
