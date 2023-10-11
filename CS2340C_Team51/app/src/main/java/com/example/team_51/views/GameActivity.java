package com.example.team_51.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team_51.map.Tilemap;
import com.example.team_51.model.Game;
import com.example.team_51.model.LeaderboardRow;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    private Button end;
    private com.example.team_51.model.Button next;
    private int character;
    private int hp;
    private String name;
    private long time;
    private final long START_SCORE = 600000;
    private CountDownTimer countDownTimer;
    private ArrayList<LeaderboardRow> leaderboardRows;
    private boolean retried;
    private int screen;
    private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game_screen);

        Intent intent = getIntent();

        hp = getIntent().getIntExtra("hp", 100);
        name = getIntent().getStringExtra("name");
        character = getIntent().getIntExtra("character", 1);
        screen = 0;


        game = new Game(this, hp, name, character, START_SCORE);
        setContentView(game);

        countDownTimer = new CountDownTimer(START_SCORE, 1000) {
            @Override
            public void onTick(long l) {
                game.updatePoints(l);
            }

            @Override
            public void onFinish() {
                game.updatePoints(0);
            }
        }.start();

        /*game.getButton().setOnClickListener(v -> {
            screen = openNextScreen(screen);
        });*/

        /*if (intent != null) {
            character = getIntent().getIntExtra("character", 1);
            hp = getIntent().getIntExtra("hp", 100);
            name = getIntent().getStringExtra("name");
            leaderboardRows = getIntent().getParcelableArrayListExtra("leaderboard");
            retried = getIntent().getBooleanExtra("retried", retried);

            countDownTimer = new CountDownTimer(START_SCORE, 1000) {
                @Override
                public void onTick(long l) {
                    time = l;
                    updateScore();
                }

                @Override
                public void onFinish() {
                    String setScore = "SCORE: 0";
                    score.setText(setScore);
                }
            }.start();
            score = (TextView) findViewById(R.id.score);


            // display character
            if (character == 1) {
                findViewById(R.id.char1).setVisibility(View.VISIBLE);
            } else if (character == 2) {
                findViewById(R.id.char2).setVisibility(View.VISIBLE);
            } else if (character == 3) {
                findViewById(R.id.char3).setVisibility(View.VISIBLE);
            }

            String level = "";

            // display health and diff
            if (hp == 100) {
                findViewById(R.id.hp1).setVisibility(View.VISIBLE);
                level = "Easy";
            } else if (hp == 50) {
                findViewById(R.id.hp2).setVisibility(View.VISIBLE);
                level = "Medium";
            } else if (hp == 30) {
                findViewById(R.id.hp3).setVisibility(View.VISIBLE);
                level = "Hard";
            }

            // update name
            TextView nametag = (TextView) findViewById(R.id.name);
            String setName = "NAME: " + name;
            nametag.setText(setName);

            // update diff
            TextView diff = (TextView) findViewById(R.id.difficulty);
            String setDiff = "DIFFICULTY: " + level;
            diff.setText(setDiff);

        }

        end = (Button) findViewById(R.id.end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEndScreen();
            }
        });*/
    }

    public void openEndScreen() {
        Intent intent = new Intent(this, EndActivity.class);
        intent.putExtra("score", time);
        intent.putExtra("name", name);
        intent.putExtra("leaderboard", leaderboardRows);
        intent.putExtra("retried", retried);
        startActivity(intent);
    }

    public int openNextScreen(int x) {
        if (x == 0) {
            game.updateTilemap(1);
        } else if (x == 1) {
            game.updateTilemap(2);
        } else {
            openEndScreen();
        }
        return x + 1;
    }
}
