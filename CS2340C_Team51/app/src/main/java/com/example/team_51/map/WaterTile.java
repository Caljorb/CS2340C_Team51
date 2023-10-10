package com.example.team_51.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.team_51.model.Sprite;
import com.example.team_51.model.SpriteSheet;

public class WaterTile extends Tile {

    private final Sprite sprite;
    public WaterTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getWaterSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
