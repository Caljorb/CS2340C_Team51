package com.example.team_51.model.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.team_51.model.Sprite;
import com.example.team_51.viewmodels.SpriteSheet;

public class BorderTile extends Tile {

    private final Sprite sprite;
    public BorderTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect); // borderTile is a wall
        sprite = spriteSheet.getBorderSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
