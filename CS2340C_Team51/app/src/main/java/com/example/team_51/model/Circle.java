package com.example.team_51.model;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.team_51.viewmodels.GameDisplay;

public abstract class Circle extends GameObject {
    protected double radius;
    protected Paint paint;

    public Circle(int color, double positionX, double positionY, double radius) {
        super(positionX, positionY);
        this.radius = radius;
        /*paint = new Paint();
        paint.setColor(color);*/
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawCircle(
                (float) gameDisplay.gameToDisplayCoordinatesX(positionX),
                (float) gameDisplay.gameToDisplayCoordinatesY(positionY),
                (float) radius,
                paint
        );
    }

    public double getRadius() {
        return radius;
    }
}
