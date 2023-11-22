package com.example.team_51.model.powers;

public class ExtraPointPower extends PowerDecorator {
    public ExtraPointPower(PowerUp powerUp) {
        super(powerUp);
    }

    @Override
    public void addPower() {
        addPointPower();
    }

    private void addPointPower() {

    }
}
