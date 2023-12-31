package com.example.team_51.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340c_team51.R;
import com.example.team_51.model.LeaderboardRow;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class InitConfigActivity extends AppCompatActivity {
    private ArrayList<LeaderboardRow> leaderboardRows;
    private boolean retried;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_config);

        leaderboardRows = getIntent().getParcelableArrayListExtra("leaderboard");
        retried = getIntent().getBooleanExtra("retried", retried);
        Button next = (Button) findViewById(R.id.nextButt);

        next.setOnClickListener(v -> {
            int hp = 0;
            int character = 0; // just represents which char picked, carry over to game

            boolean diffSel = false;
            boolean charSel = false;
            boolean nameSel = false;

            RadioGroup diffs = findViewById(R.id.diffSelect);
            RadioGroup chars = findViewById(R.id.charSelect);

            int diffsCheckedRadioButtonId = diffs.getCheckedRadioButtonId();

            // pick difficulty
            if (diffsCheckedRadioButtonId == R.id.easy) {
                hp = 100;
                diffSel = true;
            } else if (diffsCheckedRadioButtonId == R.id.medium) {
                hp = 90;
                diffSel = true;
            } else if (diffsCheckedRadioButtonId == R.id.hard) {
                hp = 80;
                diffSel = true;
            }

            int charsCheckedRadioButtonId = chars.getCheckedRadioButtonId();

            // pick character
            if (charsCheckedRadioButtonId == R.id.charSel1) {
                character = 1;
                charSel = true;
            } else if (charsCheckedRadioButtonId == R.id.charSel2) {
                character = 2;
                charSel = true;
            } else if (charsCheckedRadioButtonId == R.id.charSel3) {
                character = 3;
                charSel = true;
            }

            // pick name
            TextInputLayout nameEntry = findViewById(R.id.nameEntry);
            String name = null;
            if (nameEntry.getEditText() != null && nameEntry.getEditText().getText() != null) {
                // cannot pick null/empty/whitespace strings
                String temp = nameEntry.getEditText().getText().toString();
                if (temp.trim().length() > 0 && !temp.contains(" ")) {
                    nameSel = true;
                    name = nameEntry.getEditText().getText().toString();
                }
            }
            System.out.println("name: " + nameSel);

            // only move to next screen when all true
            if (diffSel && charSel && nameSel) {
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("character", character);
                intent.putExtra("hp", hp);
                intent.putExtra("name", name);
                intent.putExtra("leaderboard", leaderboardRows);
                intent.putExtra("retried", retried);
                startActivity(intent);
            }
        });
    }
}
