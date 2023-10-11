package com.example.team_51.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.team_51.model.SpriteSheet;

abstract class Tile {

    protected final Rect mapLocationRect;

    public Tile(Rect mapLocationRect) {
        this.mapLocationRect = mapLocationRect;
    }

    public enum TileType {
        MUD_TILE,
        STONE_TILE,
        GRASS_TILE,
        WATER_TILE,
        BORDER_TILE
    }

    public static Tile getTile(int type, SpriteSheet spriteSheet, Rect mapLocationRect) {
        switch (TileType.values()[type]) { // selects tile to make
        case MUD_TILE:
            return new MudTile(spriteSheet, mapLocationRect);
        case STONE_TILE:
            return new StoneTile(spriteSheet, mapLocationRect);
        case GRASS_TILE:
            return new GrassTile(spriteSheet, mapLocationRect);
        case WATER_TILE:
            return new WaterTile(spriteSheet, mapLocationRect);
        case BORDER_TILE:
            return new BorderTile(spriteSheet, mapLocationRect);
        default:
            return null;
        }
    }

    public abstract void draw(Canvas canvas);
}
