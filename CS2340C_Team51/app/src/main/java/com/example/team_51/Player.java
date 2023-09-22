package com.example.team_51;

public class Player {
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    public static final int MAX_HEALTH_POINTS = 5;

    private Sprite sprite;
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        sprite.draw(canvas);
        healthBar.draw(canvas, gameDisplay);
    }

}
