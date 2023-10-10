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
        } else if (map == 1) {
            createMapMid();
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

    private void createMapMid() {
        int[] borderVert = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
                            4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        int[] mud1 = {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0,
                      0, 0, 0, 0, 0, 3, 0, 0, 4};
        int[] mud2 = {4, 0, 3, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0,
                      0, 0, 0, 0, 0, 0, 3, 0, 4};
        int[] mudStone1 = {4, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1,
                           0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 4};
        int[] mudStone2 = {4, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                           0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 4};
        int[] mudExit = {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                         0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        int[] mudEntrance = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                             0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4};
        int[] mud = {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4};

        layout = new int[][] {
            borderVert,
            mudStone1,
            mud2,
            mudExit,
            mudExit,
            mud1,
            mudStone2,
            mud,
            mud,
            mud2,
            mudEntrance,
            mudEntrance,
            mudStone1,
            mud,
            borderVert
        };
    }
    
    // todo: create end map
    
    // todo: create next buttons to move maps, and move to end screen
}
