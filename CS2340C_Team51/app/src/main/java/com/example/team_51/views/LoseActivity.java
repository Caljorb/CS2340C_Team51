package com.example.team_51.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340c_team51.R;
import com.example.team_51.model.LeaderboardRow;

import java.util.ArrayList;

public class LoseActivity extends AppCompatActivity {
    private long score;
    private String name;
    private ArrayList<LeaderboardRow> leaderboardRows;
    private boolean retried;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose_screen);

        score = getIntent().getLongExtra("score", 0);
        name = getIntent().getStringExtra("name");
        leaderboardRows = getIntent().getParcelableArrayListExtra("leaderboard");
        retried = getIntent().getBooleanExtra("retried", false);

        Button retry = (Button) findViewById(R.id.loseRetry);

        retry.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("name", name);
            intent.putExtra("leaderboard", leaderboardRows);
            intent.putExtra("retried", retried);
            startActivity(intent);

        });
    }
}
