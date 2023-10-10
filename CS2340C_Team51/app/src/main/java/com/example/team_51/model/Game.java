package com.example.team_51.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.team_51.map.Tilemap;
import com.example.team_51.viewmodels.GameDisplay;

public class Game extends SurfaceView  implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private final Player player;
    private GameDisplay gameDisplay;
    private Tilemap tilemap;
    private int diff;

    public Game(Context context) {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        SpriteSheet spriteSheet = new SpriteSheet(context);
        player = Player.getPlayer(context, 0, 0, 32, 100);
        //have to account for health

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay =
                new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);

        tilemap = new Tilemap(spriteSheet);
        setFocusable(true);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        tilemap.draw(canvas, gameDisplay);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d("Game.java", "surfaceCreated()");
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            gameLoop = new GameLoop(this, surfaceHolder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.d("Game.Java", "surfaceChanged()");
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        Log.d("Game.java", "surfaceDestroyed()");
    }

    public void update() {
        gameDisplay.update();
    }
}
