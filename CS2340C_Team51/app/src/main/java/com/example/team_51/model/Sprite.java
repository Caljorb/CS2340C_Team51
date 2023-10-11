package com.example.team_51.model;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.team_51.viewmodels.SpriteSheet;

public class Sprite {

    private final SpriteSheet spriteSheet;
    private final Rect rect;

    public Sprite(SpriteSheet spriteSheet, Rect rect) {
        this.spriteSheet = spriteSheet;
        this.rect = rect;
    }
    public void draw(Canvas canvas, int x, int y) { // put sprite on screen
        canvas.drawBitmap(spriteSheet.getBitmap(),
                rect,
                new Rect(x, y, x + getWidth(), y + getHeight()),
                null);
    }

    public int getHeight() {
        return rect.height();
    }

    public int getWidth() {
        return rect.width();
    }
}
