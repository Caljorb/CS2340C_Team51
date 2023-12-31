package com.example.team_51.model.enemies;

import com.example.team_51.viewmodels.SpriteSheet;

public abstract class EnemyFactory {
    private int count;
    public Enemy create(int map, SpriteSheet spriteSheet) {
        if (count == 2) {
            count = 0;
        }

        Enemy enemy = createEnemy(spriteSheet);
        enemy.spawn(map, count);
        count++;
        return enemy;
    }

    protected abstract Enemy createEnemy(SpriteSheet spriteSheet);
}
