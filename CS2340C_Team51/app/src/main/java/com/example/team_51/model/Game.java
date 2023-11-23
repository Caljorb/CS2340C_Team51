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

import com.example.team_51.model.enemies.BatFactory;
import com.example.team_51.model.enemies.Enemy;
import com.example.team_51.model.enemies.EnemyFactory;
import com.example.team_51.model.enemies.RatFactory;
import com.example.team_51.model.enemies.SlimeFactory;
import com.example.team_51.model.enemies.SnakeFactory;
import com.example.team_51.model.map.Tilemap;
import com.example.team_51.model.powers.PowerUp;
import com.example.team_51.model.powers.PowerUpInstance;
import com.example.team_51.model.powers.SpeedPower;
import com.example.team_51.viewmodels.GameDisplay;
import com.example.team_51.viewmodels.GameLoop;
import com.example.team_51.viewmodels.SpriteSheet;
import com.example.team_51.views.GameActivity;
import com.example.team_51.views.LoseActivity;
import com.example.team_51.views.WinActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private final Player player;
    private GameDisplay gameDisplay;
    private Tilemap tilemap;
    private int diff;
    private int character;
    private long points;
    private MoveBall moveBall;
    private EnemyFactory[] enemyFactories;
    private ArrayList<Enemy> enemies;
    private int updates;
    private Enemy observer;
    private Button attackButton;
    private int attacked;
    private PowerUp powerUp;

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
        this.attackButton = new Button(context,
                new Rect(2048, 832, 2176, 896));
        attackButton.setClickable(true);

        moveBall = new MoveBall(225, 750, 80);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        SpriteSheet spriteSheet = new SpriteSheet(context);
        int[] hpChar = new int[]{diff, character};
        player = Player.getPlayer(context, 2240, 1024, moveBall, name, spriteSheet,
                hpChar);

        enemyFactories = new EnemyFactory[4];

        enemyFactories[0] = new BatFactory();
        enemyFactories[1] = new SlimeFactory();
        enemyFactories[2] = new SnakeFactory();
        enemyFactories[3] = new RatFactory();

        enemies = new ArrayList<>();

        enemies.add(enemyFactories[1].create(0, spriteSheet));
        enemies.add(enemyFactories[1].create(0, spriteSheet));
        enemies.add(enemyFactories[0].create(0, spriteSheet));
        enemies.add(enemyFactories[0].create(0, spriteSheet));

        updates = 0;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay =
                new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);

        tilemap = new Tilemap(spriteSheet, 0, player); // uses start map first
        powerUp = new SpeedPower(new PowerUpInstance(tilemap), spriteSheet);
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

        attackButton.draw(canvas, gameDisplay);
        moveBall.draw(canvas);
        player.draw(canvas, gameDisplay);

        powerUp.draw(canvas, gameDisplay);

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(canvas, gameDisplay);
        }

        paint.setColor(Color.RED);

        if (attacked != -1) {
            player.killDraw(canvas, gameDisplay);
        }
        attacked = -1;
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
        moveBall.update();
        player.update(tilemap);
        boolean swap = tilemap.update();
        gameDisplay.update();

        setGame(tilemap, updates);

        if (swap) {
            // stuff for spawning new enemies
            enemies.clear();
            if (tilemap.getMap() == 1) {
                for (int i = 0; i < 4; i++) {
                    enemies.add(i, enemyFactories[i].create(tilemap.getMap(),
                                new SpriteSheet(getContext())));
                }
            } else {
                int count = 0;
                while (count < 4) {
                    if (count < 2) {
                        enemies.add(count, enemyFactories[2].create(tilemap.getMap(),
                                new SpriteSheet(getContext())));
                    } else {
                        enemies.add(count, enemyFactories[3].create(tilemap.getMap(),
                                new SpriteSheet(getContext())));
                    }
                    count++;
                }
            }
        }
        if (updates % 5 == 0) {
            if (checkCollision()) {
                System.out.println("Player dead. Move to lose screen");

                Intent intent =
                        new Intent(GameActivity.getGameContext(), LoseActivity.class);
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

        updates++; // increment number of updates
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
                //System.out.println("Touched MoveBall");
            }

            if (attackButton.isPressed((double) event.getX(), (double) event.getY())) {
                attackButton.setIsPressed(true);
                //System.out.println("Touched Button");
                attacked = player.attack(enemies);
            }

            return true;
        case MotionEvent.ACTION_MOVE:
            if (moveBall.getIsPressed()) {
                //System.out.println("Moving");
                moveBall.setController((double) event.getX(), (double) event.getY());
            }
            return true;
        case MotionEvent.ACTION_UP:
            attackButton.setIsPressed(false);
            moveBall.setIsPressed(false);
            moveBall.resetController();
            //System.out.println("Player X: " + player.getPlayerPosX() + "\n" + "Player Y: "
            //        + player.getPlayerPosY());
            if (tilemap.getMap() > 2) {
                player.setPosX(2240);
                player.setPosY(1024);

                Intent intent =
                        new Intent(GameActivity.getGameContext(), WinActivity.class);
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
    public void setObserver(int ind) {
        observer = enemies.get(ind);
    }
    public boolean checkCollision() {
        int i = 0;
        while (i < enemies.size()) {
            setObserver(i);
            double enemyPosX = observer.getPosX();
            double enemyPosY = observer.getPosY();
            double playerPosX = player.getPlayerPosX();
            double playerPosY = player.getPlayerPosY();

            if ((Math.abs(enemyPosX - playerPosX) <= 32)
                    && (Math.abs(enemyPosY - playerPosY) <= 32)) {
                observer.observerUpdate(this);
                if (player.getHp() <= 0) {
                    return true;
                }
                if (observer.getHp() <= 0) {
                    enemies.remove(i);
                    continue;
                }
            }
            i++;
        }
        return false;
    }

    public void setGame(Tilemap tilemap, int updates) {
        for (Enemy enemy : enemies) {
            enemy.update(tilemap, updates);
        }
    }
}
