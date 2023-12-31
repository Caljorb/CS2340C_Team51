package com.example.team_51.viewmodels;

import android.graphics.Rect;

import com.example.team_51.model.GameObject;

public class GameDisplay {
    private final Rect displayRect;
    private final int widthPixels;
    private final int heightPixels;
    private final GameObject centerObject;
    private final double displayCenterX;
    private final double displayCenterY;
    private double gameCenterX;
    private double gameCenterY;
    private double displayOffsetX;
    private double displayOffsetY;

    public GameDisplay(int widthPixels, int heightPixels, GameObject centerObject) {
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
        displayRect = new Rect(0, 0, widthPixels, heightPixels);

        this.centerObject = centerObject;

        displayCenterX = widthPixels / 2.0;
        displayCenterY = heightPixels / 2.0;

        update();
    }

    public void update() {
        gameCenterX = centerObject.getPositionX();
        gameCenterY = centerObject.getPositionY();

        displayOffsetX = displayCenterX - gameCenterX;
        displayOffsetY = displayCenterY - gameCenterY;
    }

    public double gameToDisplayCoordinatesX(double x) {
        return x + displayOffsetX;
    }

    public double gameToDisplayCoordinatesY(double y) {
        return y + displayOffsetY;
    }

    public Rect getGameRect() {
        return new Rect(
                (int) ((gameCenterX - widthPixels) / 2),
                (int) ((gameCenterY - heightPixels) / 2),
                (int) ((gameCenterX + widthPixels) / 2),
                (int) ((gameCenterY + heightPixels) / 2)
        );
    }

    public Rect getDisplayRect() {
        return displayRect;
    }
}
