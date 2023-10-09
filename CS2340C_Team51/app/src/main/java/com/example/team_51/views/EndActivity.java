package com.example.team_51.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340c_team51.R;
import com.example.team_51.model.LeaderboardRow;
import com.example.team_51.viewmodels.LeaderboardViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EndActivity extends AppCompatActivity {
    private LeaderboardViewModel leaderboardViewModel;
    private ArrayList<LeaderboardRow> leaderboardRows;
    private long score;
    private String name;
    private String date;
    private boolean retried;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        leaderboardViewModel = LeaderboardViewModel.getLeaderboardViewModel();
        leaderboardRows = getIntent().getParcelableArrayListExtra("leaderboard");
        retried = getIntent().getBooleanExtra("retried", retried);
        System.out.println("Retried: " + retried);
        if (retried) { // only update viewmodel when have retried before
            leaderboardViewModel.setRows(leaderboardRows);
        }

        score = getIntent().getLongExtra("score", 0);
        name = getIntent().getStringExtra("name");

        LeaderboardRow attempt = new LeaderboardRow(name, score, date);

        // set date for recent attempt
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date d = new Date();
        date = simpleDateFormat.format(d);

        // set recent attempt
        TextView nameR = findViewById(R.id.nameR);
        nameR.setText(name);
        TextView scoreR = findViewById(R.id.scoreR);
        String setScore = " " + score + " ";
        scoreR.setText(setScore);
        TextView dateR = findViewById(R.id.dateR);
        dateR.setText(date);

        // add recent attempt to leaderboardViewModel if top score
        leaderboardViewModel.addRow(attempt);
        leaderboardViewModel.sortRows();
        leaderboardViewModel.clearRows(); //clears scores outside of top 5

        // display leaderboard

        // button to retry
        Button retry = findViewById(R.id.retry);

        retry.setOnClickListener(v -> {
            retried = true;
            Intent intent = new Intent(this, InitConfigActivity.class);
            intent.putExtra("leaderboard", leaderboardViewModel.getLeaderboardRows());
            intent.putExtra("retried", retried);
            startActivity(intent);
        });
    }
}
