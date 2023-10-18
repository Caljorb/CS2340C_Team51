package com.example.team_51.model.map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.team_51.model.Button;
import com.example.team_51.model.Player;
import com.example.team_51.viewmodels.SpriteSheet;
import com.example.team_51.viewmodels.GameDisplay;

public class Tilemap {

    private MapLayout mapLayout;
    private Tile[][] tilemap;
    private SpriteSheet spriteSheet;
    private Bitmap mapBitmap;
    private int map;
    private boolean swap;
    //private Button button;
    private Player player;
    private double exitYTop;
    private double exitYBottom;

    public Tilemap(SpriteSheet spriteSheet, int map, Player player) {
        this.map = map;
        setExitY(map);
        this.player = player;
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
        // Game Plan: swap = true if you get the position for exit on correct map
        // each map must have position for exit
        // update last map to have an exit
        //

        boolean swap = false;

        if (player.getPlayerPosX() > 3236
                && (player.getPlayerPosY() >  exitYTop && player.getPlayerPosY() < exitYBottom)) {
            swap = true;
            System.out.println("Exit");
        }

        if (swap) { // swap to new map when button pressed
            incrementMap();
            setExitY(map);
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

    private void setExitY(int map) {
        if (map == 0) {
            exitYTop = 1145;
            exitYBottom = 1210;
        } else if (map == 1) {
            exitYTop = 690;
            exitYBottom = 780;
        } else {
            exitYTop = 1192;
            exitYBottom = 1273;
        }
    }
}
