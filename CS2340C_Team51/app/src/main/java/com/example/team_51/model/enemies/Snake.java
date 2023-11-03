package com.example.team_51.model.enemies;

import com.example.team_51.model.Sprite;
import com.example.team_51.viewmodels.SpriteSheet;

public class Snake implements Enemy {
    private double posX;
    private double posY;
    private Sprite sprite;
    private int hp;

    Snake(SpriteSheet spriteSheet) {
        this.hp = 20;
        this.sprite = spriteSheet.getEnemySprite(3);
    }
    @Override
    public void spawn(int map) {

    }
}
