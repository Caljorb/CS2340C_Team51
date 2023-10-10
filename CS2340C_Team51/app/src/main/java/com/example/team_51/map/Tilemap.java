package com.example.team_51.map;

import static com.example.team_51.map.MapLayout.NUMBER_OF_ROW_TILES;
import static com.example.team_51.map.MapLayout.NUMBER_OF_COLUMN_TILES;
import static com.example.team_51.map.MapLayout.TILE_HEIGHT_PIXELS;
import static com.example.team_51.map.MapLayout.TILE_WIDTH_PIXELS;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.team_51.model.SpriteSheet;

public class Tilemap {

    private final MapLayout mapLayout;
    private Tile[][] tilemap;
    private SpriteSheet spriteSheet;
    private Bitmap mapBitmap;

    public Tilemap(SpriteSheet spriteSheet) {
        mapLayout = new MapLayout();
        this.spriteSheet = spriteSheet;
        createTilemap();
    }

    private void createTilemap() {
        int[][] layout = mapLayout.getLayout();
        tilemap = new Tile[NUMBER_OF_ROW_TILES][NUMBER_OF_COLUMN_TILES];
        for (int r = 0; r < NUMBER_OF_ROW_TILES; r++) {
            for (int c = 0; c < NUMBER_OF_COLUMN_TILES; c++) {
                tilemap[r][c] = Tile.getTile(
                        layout[r][c], spriteSheet, getRectByIndex(r, c));
            }
        }
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        mapBitmap = Bitmap.createBitmap(
                NUMBER_OF_COLUMN_TILES * TILE_WIDTH_PIXELS,
                NUMBER_OF_ROW_TILES * TILE_HEIGHT_PIXELS,
                config
        );

        Canvas mapCanvas = new Canvas(mapBitmap);
        for (int r = 0; r < NUMBER_OF_ROW_TILES; r++) {
            for (int c = 0; c < NUMBER_OF_COLUMN_TILES; c++) {
                tilemap[r][c].draw(mapCanvas);
            }
        }

    }



    private Rect getRectByIndex(int r, int c) {
        return new Rect(c * TILE_WIDTH_PIXELS,
                r * TILE_HEIGHT_PIXELS,
                (c + 1) * TILE_WIDTH_PIXELS,
                (r + 1) * TILE_HEIGHT_PIXELS
        );
    }
}
