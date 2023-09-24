package com.example.team_51;

import android.content.Context;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.cs2340c_team51.R;

public class HealthBar {
    private Player player;
    private int width;
    private int height;
    private int margin;
    private Paint borderPaint;
    private Paint healthPaint;
    public HealthBar(Context context, Player player) {
        this.player = player;
        this.width = 100;
        this.height = 20;
        this.margin = 2;
        this.borderPaint = new Paint();
        int borderColor = ContextCompat.getColor(context, R.color.border);
        borderPaint.setColor(borderColor);

        this.healthPaint = new Paint();
        int healthColor = ContextCompat.getColor(context, R.color.health);
        borderPaint.setColor(healthColor);
    }
//    public void draw(Canvas canvas) {
//        float x = (float) player.getPlayerPosX();
//        float y = (float) player.getPlayerPosY();
//        float distFromPlayer = 30;
//        float healthPercent = (float) player.getHp() / player.maxHp;
//
//        float borderLeft;
//        float borderRight;
//        float borderTop;
//        float borderBottom;
//        borderLeft = x - width / 2;
//        borderRight = x + width / 2;
//        borderBottom = y - distFromPlayer;
//        borderTop = borderBottom - height;
//        //Drawing border
//        canvas.drawRect(borderLeft, borderTop, borderRight, borderBottom, borderPaint);
//
//        float healthWidth = width - 2 * margin;
//        float healthHeight = height - 2 * margin;
//        float healthLeft = borderLeft + margin;
//        float healthRight = healthLeft + healthWidth * healthPercent;
//        float healthBottom = borderBottom - margin;
//        float healthTop = healthBottom - healthHeight;
//        //Drawing health
//        canvas.drawRect(healthLeft, healthTop, healthRight, healthBottom, healthPaint);
//    }
}
