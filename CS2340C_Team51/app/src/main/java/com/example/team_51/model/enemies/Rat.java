package com.example.team_51.model.enemies;

import com.example.team_51.model.Sprite;
import com.example.team_51.viewmodels.SpriteSheet;

public class Rat implements Enemy {
    private double posX;
    private double posY;
    private Sprite sprite;
    private int hp;

    Rat(SpriteSheet spriteSheet) {
        this.hp = 25;
        this.sprite = spriteSheet.getEnemySprite(4);
    }

    @Override
    public void spawn(int map) {

    }
}
