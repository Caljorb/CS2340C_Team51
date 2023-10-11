package com.example.team_51.views;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team_51.model.Game;

public class GameActivity extends AppCompatActivity {
    private static Context context;
    private int character;
    private int hp;
    private String name;
    private final long startScore = 600000;
    private CountDownTimer countDownTimer;
    private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameActivity.context = getApplicationContext();

        hp = getIntent().getIntExtra("hp", 100);
        name = getIntent().getStringExtra("name");
        character = getIntent().getIntExtra("character", 1);

        game = new Game(this, hp, name, character, startScore);
        setContentView(game);

        countDownTimer = new CountDownTimer(startScore, 1000) {
            @Override
            public void onTick(long l) {
                game.updatePoints(l);
            }

            @Override
            public void onFinish() {
                game.updatePoints(0);
            }
        }.start();
    }

    public static Context getGameContext() {
        return GameActivity.context;
    }
}
