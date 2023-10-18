package com.example.team_51.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MoveBall {
    private int bigCircCentX;
    private int bigCircCentY;
    private int smallCircCentX;
    private int smallCircCentY;
    private Paint bigPaint;
    private Paint smallPaint;
    private int bigRadius;
    private int smallRadius;
    private boolean isPressed;
    private double controllerX;
    private double controllerY;

    public MoveBall(int centX, int centY, int radius) {
        // Center
        bigCircCentX = centX;
        bigCircCentY = centY;
        smallCircCentX = centX;
        smallCircCentY = centY;

        // Color
        bigPaint = new Paint();
        bigPaint.setColor(Color.WHITE);
        bigPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        smallPaint = new Paint();
        smallPaint.setColor(Color.CYAN);
        smallPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        // Radius
        bigRadius = radius;
        smallRadius = radius / 2;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(bigCircCentX, bigCircCentY, bigRadius, bigPaint);
        canvas.drawCircle(smallCircCentX, smallCircCentY, smallRadius, smallPaint);
    }

    public void update() {
        updateSmallCircle();
    }

    private void updateSmallCircle() {
        // center at touch position
        smallCircCentX = (int) (bigCircCentX + controllerX * bigRadius);
        smallCircCentY = (int) (bigCircCentY + controllerY * bigRadius);
    }

    public boolean isPressed(double x, double y) {
        //double touchVectorDist = Math.sqrt(Math.pow(bigCircCentX - x, 2)
        //        + Math.pow(bigCircCentY - y, 2)); // instance var??
        return getDistance(x, y) < bigRadius; // if vector from center to touchPos < radius, true
    }

    public void setIsPressed(boolean pressed) {
        this.isPressed = pressed;
    }

    public boolean getIsPressed() {
        return isPressed;
    }

    public void setController(double x, double y) {
        double vectorX = x - bigCircCentX;
        double vectorY = y - bigCircCentY;
        double distance = getDistance(x, y);

        if (distance < bigRadius) {
            controllerX = vectorX / bigRadius; // how much you should move in x direction
            controllerY = vectorY / bigRadius; // how much you should move in y direction
        } else {
            // cannot let value go past max distance
            controllerX = vectorX / distance;
            controllerY = vectorY / distance;
        }
    }

    public void resetController() {
        controllerX = 0;
        controllerY = 0;
    }

    private double getDistance(double x, double y) {
        return Math.sqrt(Math.pow(bigCircCentX - x, 2) + Math.pow(bigCircCentY - y, 2));
    }

    public double getControllerX() {
        return controllerX;
    }

    public double getControllerY() {
        return controllerY;
    }
}
