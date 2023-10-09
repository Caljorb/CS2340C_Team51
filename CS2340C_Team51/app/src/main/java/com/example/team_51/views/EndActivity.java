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
        if (retried) { // only update viewmodel when have retried before
            leaderboardViewModel.setRows(leaderboardRows);
        }

        score = getIntent().getLongExtra("score", 0);
        name = getIntent().getStringExtra("name");

        // set date for recent attempt
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date d = new Date();
        date = simpleDateFormat.format(d);

        LeaderboardRow attempt = new LeaderboardRow(name, score, date);

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
        //leaderboardViewModel.clearRows(); //clears scores outside of top 5
        System.out.println(leaderboardViewModel);

        // display leaderboard
        TextView name1 = findViewById(R.id.name1);
        name1.setText(leaderboardViewModel.getLeaderboardRow(0).getName());
        TextView score1 = findViewById(R.id.score1);
        String setScore1 = " " + leaderboardViewModel.getLeaderboardRow(0).getScore() + " ";
        score1.setText(setScore1);
        TextView date1 = findViewById(R.id.date1);
        date1.setText(leaderboardViewModel.getLeaderboardRow(0).getDate());

        TextView name2 = findViewById(R.id.name2);
        name2.setText(leaderboardViewModel.getLeaderboardRow(1).getName());
        TextView score2 = findViewById(R.id.score2);
        String setScore2 = " " + leaderboardViewModel.getLeaderboardRow(1).getScore() + " ";
        score2.setText(setScore2);
        TextView date2 = findViewById(R.id.date2);
        date2.setText(leaderboardViewModel.getLeaderboardRow(1).getDate());

        TextView name3 = findViewById(R.id.name3);
        name3.setText(leaderboardViewModel.getLeaderboardRow(2).getName());
        TextView score3 = findViewById(R.id.score3);
        String setScore3 = " " + leaderboardViewModel.getLeaderboardRow(2).getScore() + " ";
        score3.setText(setScore3);
        TextView date3 = findViewById(R.id.date3);
        date3.setText(leaderboardViewModel.getLeaderboardRow(2).getDate());

        TextView name4 = findViewById(R.id.name4);
        name4.setText(leaderboardViewModel.getLeaderboardRow(3).getName());
        TextView score4 = findViewById(R.id.score4);
        String setScore4 = " " + leaderboardViewModel.getLeaderboardRow(3).getScore() + " ";
        score4.setText(setScore4);
        TextView date4 = findViewById(R.id.date4);
        date4.setText(leaderboardViewModel.getLeaderboardRow(3).getDate());

        TextView name5 = findViewById(R.id.name5);
        name5.setText(leaderboardViewModel.getLeaderboardRow(4).getName());
        TextView score5 = findViewById(R.id.score5);
        String setScore5 = " " + leaderboardViewModel.getLeaderboardRow(4).getScore() + " ";
        score5.setText(setScore5);
        TextView date5 = findViewById(R.id.date5);
        date5.setText(leaderboardViewModel.getLeaderboardRow(4).getDate());


        // button to retry
        Button retry = findViewById(R.id.retry);

        retry.setOnClickListener(v -> {
            retried = true;
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("leaderboard", leaderboardViewModel.getLeaderboardRows());
            intent.putExtra("retried", retried);
            startActivity(intent);
        });
    }
}
