package com.example.team_51.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.team_51.viewmodels.GameDisplay;

public class Button extends androidx.appcompat.widget.AppCompatButton {

    private Rect rect;
    private boolean isPressed;

    private Button(Context context) {
        super(context);
    }

    public Button(Context context, Rect rect) {
        this(context);
        this.rect = rect;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawRect(rect, paint); // draw button
    }

    public void update() {

    }

    public boolean isPressed(double touchPosX, double touchPosY) {
        return ((touchPosX > rect.left && touchPosX < rect.right)
                && touchPosY > rect.top && touchPosY < rect.bottom); // check if pressed button
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public boolean getIsPressed() {
        return isPressed;
    }
}
