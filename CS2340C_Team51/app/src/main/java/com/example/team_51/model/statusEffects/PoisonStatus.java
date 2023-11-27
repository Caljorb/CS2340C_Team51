package com.example.team_51.model.statusEffects;

import com.example.team_51.viewmodels.SpriteSheet;

public class PoisonStatus extends StatusDecorator {
    public PoisonStatus(StatusEffect se, SpriteSheet spritesheet) {
        super(se);
        super.sprite = spritesheet.getSESprite(0);
    }
    public double addSE() {
        ((StatusInstance) super.se).setPosX(100000);
        ((StatusInstance) super.se).setPosY(100000);
        return addPoison();
    }
    public double addPoison() {
        return -2;
    }
}
