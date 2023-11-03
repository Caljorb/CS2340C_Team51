package com.example.team_51.model.enemies;

import com.example.team_51.model.Sprite;
import com.example.team_51.viewmodels.SpriteSheet;

public class Bat implements Enemy {
    private double posX;
    private double posY;
    private Sprite sprite;
    private int hp;

    Bat(SpriteSheet spriteSheet) {
        this.hp = 15;
        this.sprite = spriteSheet.getEnemySprite(1);
        // initializes traits of bat
    }

    @Override
    public void spawn(int map) {
        // sets initial location for spawn based on map
    }
}
