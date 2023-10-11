package com.example.team_51.model.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.team_51.model.Sprite;
import com.example.team_51.viewmodels.SpriteSheet;

public class StoneTile extends Tile {

    private final Sprite sprite;
    public StoneTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getStoneSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
