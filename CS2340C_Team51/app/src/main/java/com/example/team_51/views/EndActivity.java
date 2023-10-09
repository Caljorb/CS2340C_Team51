package com.example.team_51.views;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340c_team51.R;
import com.example.team_51.model.LeaderboardRow;
import com.example.team_51.viewmodels.LeaderboardViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EndActivity extends AppCompatActivity {
    private LeaderboardViewModel leaderboardViewModel;
    private long score;
    private String name;
    private String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        leaderboardViewModel = new LeaderboardViewModel();
        score = getIntent().getLongExtra("score", 0);
        name = getIntent().getStringExtra("name");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date d = new Date();
        date = simpleDateFormat.format(d);

        //leaderboardViewModel.setRow(0, name, score, date);

        TextView nameR = findViewById(R.id.nameR);
        nameR.setText(name);
        TextView scoreR = findViewById(R.id.scoreR);
        String setScore = " " + score + " ";
        scoreR.setText(setScore);
        TextView dateR = findViewById(R.id.dateR);
        dateR.setText(date);

    }

    private int findRow(LeaderboardRow[] leaderboardRows, long score) {
        int rtn = 0;
        for (int i = 0; i < 5; i++) {
            if (score >= leaderboardRows[i].getScore()) {

            }
        }
        return 0;
    }

    private void shiftRows(LeaderboardRow[] leaderboardRows, int index) {

    }
}
