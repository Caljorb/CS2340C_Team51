package com.example.team_51.model.powers;


import android.graphics.Canvas;

import com.example.team_51.model.map.Tilemap;
import com.example.team_51.viewmodels.GameDisplay;

public class PowerUpInstance implements PowerUp {
    private double posX;
    private double posY;
    public PowerUpInstance(Tilemap tilemap) {
        double[] temp = randomSpawn(tilemap);
        this.posX = temp[0];
        this.posY = temp[1];
    }

    private double[] randomSpawn(Tilemap tilemap) {
        double posX = 1120 + (Math.random() * 2000); // limit bounds
        double posY = 500 + (Math.random() * 900); // limit bounds

        while (isWall(tilemap, posX, posY) || checkOutOfBounds(posX, posY)) {
            posX = 1120 + (Math.random() * 2000);
            posY = 500 + (Math.random() * 900);
            //System.out.println("isWall: " + isWall(tilemap, posX, posY));
            //System.out.println("checkOOB: " + !checkOutOfBounds(posX, posY));
        }
        return new double[] {posX, posY};
    }

    private boolean isWall(Tilemap tilemap, double posX, double posY) {
        int[][] walls = tilemap.getWalls();

        double tileX = 1117.4; // where tile 1 starts X
        double tileY = 500.6; // where tile 1 starts Y

        int c = (int) ((posX - tileX + 32) / 64.0); // find tile index based on pos
        int r = (int) ((posY - tileY + 32) / 64.0);

        try {
            if (walls[r][c] == 3 || walls[r][c] == 4) {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            return true; // position oob sometimes
        }

        return false; // not in any walls
    }

    private boolean checkOutOfBounds(double posX, double posY) {
        boolean xIn = posX > 1110 && posX < 3300;
        boolean yIn = posY > 500 && posY < 1400;

        return !(xIn && yIn);
    }

    @Override
    public int addPower() {
        return 1;
    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {

    }

    @Override
    public void update(Tilemap tilemap) {

    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public void setPos(Tilemap tilemap) {
        double[] temp = randomSpawn(tilemap);
        this.posX = temp[0];
        this.posY = temp[1];
    }
}
