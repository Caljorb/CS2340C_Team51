package com.example.team_51.model.enemies;

import com.example.team_51.viewmodels.SpriteSheet;

public abstract class EnemyFactory {
    public Enemy create(int map, SpriteSheet spriteSheet) {
        Enemy enemy = createEnemy(spriteSheet);
        enemy.spawn(map);
        return enemy;
    }

    protected abstract Enemy createEnemy(SpriteSheet spriteSheet);
}
