package com.example.team_51.model.powers;

public class SpeedPower extends PowerDecorator {
    public SpeedPower(PowerUp powerUp) {
        super(powerUp);
    }

    @Override
    public int addPower() {
        return addSpeedPower();
    }

    private int addSpeedPower() {
        return 2;
    }
}
