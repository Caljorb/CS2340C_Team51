package com.example.team_51.model.powers;

public class SpeedPower extends PowerDecorator {
    public SpeedPower(PowerUp powerUp) {
        super(powerUp);
    }

    @Override
    public void addPower() {
        addSpeedPower();
    }

    private void addSpeedPower() {

    }
}
