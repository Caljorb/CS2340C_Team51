package com.example.team_51.model.powers;

public class ExtraPointPower extends PowerDecorator {
    public ExtraPointPower(PowerUp powerUp) {
        super(powerUp);
    }

    @Override
    public int addPower() {
        return addPointPower();
    }

    private int addPointPower() {
        return 10000;
    }
}
