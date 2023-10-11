package com.example.team_51.model.map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.team_51.model.Button;
import com.example.team_51.model.SpriteSheet;
import com.example.team_51.viewmodels.GameDisplay;

public class Tilemap {

    private MapLayout mapLayout;
    private Tile[][] tilemap;
    private SpriteSheet spriteSheet;
    private Bitmap mapBitmap;
    private int map;
    private boolean swap;
    private Button button;

    public Tilemap(SpriteSheet spriteSheet, int map, Button button) {
        this.map = map;
        this.button = button;
        mapLayout = new MapLayout(map);
        this.spriteSheet = spriteSheet;
        createTilemap();
    }

    private void createTilemap() {
        // construct map layout
        int[][] layout = mapLayout.getLayout();
        tilemap = new Tile[MapLayout.NUMBER_OF_ROW_TILES][MapLayout.NUMBER_OF_COLUMN_TILES];
        for (int r = 0; r < MapLayout.NUMBER_OF_ROW_TILES; r++) {
            for (int c = 0; c < MapLayout.NUMBER_OF_COLUMN_TILES; c++) {
                tilemap[r][c] = Tile.getTile(
                        layout[r][c], spriteSheet, getRectByIndex(r, c));
            }
        }
        // construct bitmap
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        mapBitmap = Bitmap.createBitmap(
                MapLayout.NUMBER_OF_COLUMN_TILES * MapLayout.TILE_WIDTH_PIXELS,
                MapLayout.NUMBER_OF_ROW_TILES * MapLayout.TILE_HEIGHT_PIXELS,
                config
        );
        // draw bitmap
        Canvas mapCanvas = new Canvas(mapBitmap);
        for (int r = 0; r < MapLayout.NUMBER_OF_ROW_TILES; r++) {
            for (int c = 0; c < MapLayout.NUMBER_OF_COLUMN_TILES; c++) {
                tilemap[r][c].draw(mapCanvas);
            }
        }

    }

    //selects single tile
    private Rect getRectByIndex(int r, int c) {
        return new Rect(c * MapLayout.TILE_WIDTH_PIXELS,
                r * MapLayout.TILE_HEIGHT_PIXELS,
                (c + 1) * MapLayout.TILE_WIDTH_PIXELS,
                (r + 1) * MapLayout.TILE_HEIGHT_PIXELS
        );
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawBitmap(
                mapBitmap,
                gameDisplay.getGameRect(),
                gameDisplay.getDisplayRect(),
                null
        );
    }

    public void updateMap(int map) {
        mapLayout = new MapLayout(map);
    }

    public void update() {
        swap = button.getIsPressed();
        if (swap) { // swap to new map when button pressed
            System.out.println("Map: " + map);
            updateMap(map);
            createTilemap(); // make new map
        }
    }

    public int getMap() {
        return map;
    }

    public void incrementMap() {
        map++;
    } // update map number
}
