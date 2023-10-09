package com.example.team_51.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340c_team51.R;
import com.example.team_51.model.LeaderboardRow;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private Button quit;
    private ArrayList<LeaderboardRow> leaderboardRows;
    private boolean retried;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        leaderboardRows = getIntent().getParcelableArrayListExtra("leaderboard");
        retried = getIntent().getBooleanExtra("retried", retried);

        start = (Button) findViewById(R.id.startButton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInitConfigActivity();
            }
        });

        quit = (Button) findViewById(R.id.quitButton);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
    }

    public void openInitConfigActivity() {
        Intent intent = new Intent(this, InitConfigActivity.class);
        intent.putExtra("leaderboard", leaderboardRows);
        intent.putExtra("retried", retried);
        startActivity(intent);
    }

}
