package com.example.team_51.model.enemies;

import com.example.team_51.viewmodels.SpriteSheet;

public class BatFactory extends EnemyFactory {

    @Override
    protected Enemy createEnemy(SpriteSheet spriteSheet) {
        return new Bat(spriteSheet);
    }
}
