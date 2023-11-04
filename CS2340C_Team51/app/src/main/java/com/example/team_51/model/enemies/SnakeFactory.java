package com.example.team_51.model.enemies;

import com.example.team_51.viewmodels.SpriteSheet;

public class SnakeFactory extends EnemyFactory {

    @Override
    protected Enemy createEnemy(SpriteSheet spriteSheet) {
        return new Snake(spriteSheet);
    }
}
