package com.example.team_51.model.enemies;

import com.example.team_51.model.Sprite;
import com.example.team_51.viewmodels.SpriteSheet;

public class Slime implements Enemy {
    private double posX;
    private double posY;
    private Sprite sprite;
    private int hp;

    Slime(SpriteSheet spriteSheet) {
        this.hp = 10;
        this.sprite = spriteSheet.getEnemySprite(2);
    }
    @Override
    public void spawn(int map) {

    }
}
