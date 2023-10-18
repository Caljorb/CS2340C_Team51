package com.example.team_51.model;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.team_51.model.map.Tilemap;
import com.example.team_51.viewmodels.GameDisplay;
import com.example.team_51.viewmodels.GameLoop;
import com.example.team_51.viewmodels.SpriteSheet;
import com.example.team_51.views.EndActivity;
import com.example.team_51.views.GameActivity;

import java.util.ArrayList;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private final Player player;
    private GameDisplay gameDisplay;
    private Tilemap tilemap;
    private int diff;
    private int character;
    private long points;
    private Button button;
    private MoveBall moveBall;

    public Game(int diff, String name, int character, long points) {
        super(null);
        this.diff = diff;
        this.character = character;
        this.points = points;

        int[] hpChar = new int[]{diff, character};
        player = Player.getPlayer(null, 2240, 1024, 32, name,
                new SpriteSheet(null), hpChar);
    }


    public Game(Context context, int diff, String name, int character, long points) {
        super(context);

        this.diff = diff;
        this.character = character;
        this.points = points;
        this.button = new Button(context, new Rect(2048, 832, 2176, 896));
        button.setClickable(true);

        moveBall = new MoveBall(225, 750, 80);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        SpriteSheet spriteSheet = new SpriteSheet(context);
        int[] hpChar = new int[]{diff, character};
        player = Player.getPlayer(context, 2240, 1024, moveBall, name, spriteSheet,
                hpChar);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay =
                new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);

        tilemap = new Tilemap(spriteSheet, 0, button); // uses start map first
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
        canvas.drawText("Score: " + points, 80, 250, paint);

        button.draw(canvas, gameDisplay);
        moveBall.draw(canvas);
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
        button.update();
        moveBall.update();
        player.update();
        tilemap.update();
        gameDisplay.update();
    }

    public String diffSelect(int diff) {
        if (diff == 100) {
            return "Easy";
        } else if (diff == 50) {
            return "Medium";
        } else {
            return "Hard";
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // button presses
        switch (event.getActionMasked()) {
        case MotionEvent.ACTION_DOWN:
            if (moveBall.isPressed((double) event.getX(), (double) event.getY())) {
                moveBall.setIsPressed(true);
                System.out.println("Touched MoveBall");
            }

            if (button.isPressed((double) event.getX(), (double) event.getY())) {
                button.setIsPressed(true);
                tilemap.incrementMap();
                if (tilemap.getMap() > 2) {
                    // button opens end screen
                    // and passes all data
                    Intent intent =
                                new Intent(GameActivity.getGameContext(), EndActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // allow starting activity outside activity class
                    intent.putExtra("score", points);
                    intent.putExtra("name", player.getName());
                    ArrayList<LeaderboardRow> leaderboardRows = intent.
                                getParcelableArrayListExtra("leaderboard");
                    intent.putExtra("leaderboard", leaderboardRows);
                    boolean retried = intent.getBooleanExtra("retried", false);
                    intent.putExtra("retried", retried);
                    GameActivity.getGameContext().startActivity(intent);
                }
            }
            return true;
        case MotionEvent.ACTION_MOVE:
            if (moveBall.getIsPressed()) {
                moveBall.setController((double) event.getX(), (double) event.getY());
                System.out.println("Moving MoveBall");
            }
            return true;
        case MotionEvent.ACTION_UP:
            button.setIsPressed(false);
            moveBall.setIsPressed(false);
            moveBall.resetController();
            System.out.println("Player X: " + player.getPlayerPosX() + "\n" + "Player Y: "
                    + player.getPlayerPosY());
            return true;
        default:

        }
        return super.onTouchEvent(event);
    }

    public void updatePoints(long points) {
        this.points = points;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean checkPoints(long points) {
        return points >= 0;
    }

    public long getPoints() {
        return points;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }
}
