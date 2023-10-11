package com.example.team_51.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.team_51.viewmodels.GameDisplay;

public class Button extends androidx.appcompat.widget.AppCompatButton {

    private Rect rect;
    private String text;
    private boolean isPressed;

    private Button(Context context) {
        super(context);
    }

    public Button(Context context, Rect rect, String text) {
        this(context);
        this.rect = rect;
        this.text = text;
        this.setText(text);
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawRect(rect, paint);
    }

    public void update() {

    }

    public boolean isPressed(double touchPosX, double touchPosY) {
        return ((touchPosX > rect.left && touchPosX < rect.right)
                && touchPosY > rect.top && touchPosY < rect.bottom);
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public boolean getIsPressed() {
        return isPressed;
    }
}
