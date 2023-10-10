package com.example.team_51.map;

public class MapLayout {
    public static final int TILE_WIDTH_PIXELS = 64;
    public static final int TILE_HEIGHT_PIXELS = 64;
    public static final int NUMBER_OF_ROW_TILES = 15; // 15
    public static final int NUMBER_OF_COLUMN_TILES = 35; // 35

    private int[][] layout;
    public MapLayout(int map) {
        createLayout(map);
    }

    public int[][] getLayout() {
        return layout;
    }

    private void createLayout(int map) {
        if (map == 0) {
            createMapStart();
        }
    }

    private void createMapStart() {
        int[] borderVert = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
                            4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        int[] grass = {4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                       2, 2, 2, 2, 2, 2, 2, 2, 4};
        int[] grassWater = {4, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                            2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4};
        int[] grassExit = {4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                           2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1};

        layout = new int[][] {
            borderVert,
            grass,
            grass,
            grass,
            grass,
            grass,
            grassWater,
            grassWater,
            grassWater,
            grass,
            grassExit,
            grassExit,
            grass,
            grass,
            borderVert
        };
    }
}
