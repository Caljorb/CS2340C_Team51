package com.example.team_51.model.powers;

import com.example.team_51.model.Sprite;
import com.example.team_51.viewmodels.SpriteSheet;

public class PowerUpInstance implements PowerUp {
    private Sprite sprite;
    private double posX;
    private double posY;
    private int boost;

    public PowerUpInstance(int power, SpriteSheet spriteSheet) {
        this.sprite = spriteSheet.getPowerSprite(power);
    }

    @Override
    public int addPower() {
        return 1;
    }
}
