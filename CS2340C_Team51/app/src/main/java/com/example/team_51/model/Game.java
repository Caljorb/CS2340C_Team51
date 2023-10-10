package com.example.team_51.model;

import static com.example.team_51.map.MapLayout.TILE_HEIGHT_PIXELS;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.team_51.map.Tilemap;
import com.example.team_51.viewmodels.GameDisplay;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private final Player player;
    private GameDisplay gameDisplay;
    private Tilemap tilemap;
    private int diff;

    public Game(Context context, int diff, String name) {
        super(context);

        this.diff = diff;

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        SpriteSheet spriteSheet = new SpriteSheet(context);
        player = Player.getPlayer(context, 2240, 1024, 32, diff, name);
        // need sprite

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay =
                new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);

        tilemap = new Tilemap(spriteSheet, 0); // uses start map first
        setFocusable(true);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        tilemap.draw(canvas, gameDisplay);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(48f);
        canvas.drawText("Difficulty: " + diffSelect(diff), 80, 200, paint);

        player.draw(canvas, gameDisplay);
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

    private String diffSelect(int diff) {
        if (diff == 100) {
            return "Easy";
        } else if (diff == 50) {
            return "Medium";
        } else {
            return "Hard";
        }
    }
}
