package com.example.team_51.viewmodels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.cs2340c_team51.R;
import com.example.team_51.model.Sprite;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 64;
    private static final int SPRITE_HEIGHT_PIXELS = 64;
    private Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        try {
            bitmap = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.sprite_sheet, bitmapOptions);
        } catch (NullPointerException npe) {
            bitmap = null;
        }
    }

    public Sprite getPlayerSprite(int character) {
        return getSpriteByIndex(1, character - 1);
    }

    public Sprite getEnemySprite(int enemy) {
        if (enemy < 3) {
            return getSpriteByIndex(1, enemy + 2);
        } else {
            return getSpriteByIndex(2, enemy - 3);
        }
    }

    public Sprite getPowerSprite(int power) {
        return getSpriteByIndex(0, power);
    }

    public Sprite getSwordSprite() {
        return getSpriteByIndex(2, 2);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getMudSprite() {
        return getSpriteByIndex(0, 0);
    }

    public Sprite getStoneSprite() {
        return getSpriteByIndex(0, 1);
    }

    public Sprite getGrassSprite() {
        return getSpriteByIndex(0, 2);
    }

    public Sprite getWaterSprite() {
        return getSpriteByIndex(0, 3);
    }

    public Sprite getBorderSprite() {
        return getSpriteByIndex(0, 4);
    }

    private Sprite getSpriteByIndex(int r, int c) {
        return new Sprite(this, new Rect(c * SPRITE_WIDTH_PIXELS,
                r * SPRITE_HEIGHT_PIXELS,
                (c + 1) * SPRITE_WIDTH_PIXELS,
                (r + 1) * SPRITE_HEIGHT_PIXELS));
    }


}
