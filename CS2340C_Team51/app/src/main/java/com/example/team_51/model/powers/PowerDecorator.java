package com.example.team_51.model.powers;

public abstract class PowerDecorator implements PowerUp {
    private PowerUp powerUp;

    public PowerDecorator(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    @Override
    public void addPower() {
        powerUp.addPower();
    }
}
