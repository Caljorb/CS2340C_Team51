package com.example.team_51;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340c_team51.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class InitConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_config);

        Button next = (Button) findViewById(R.id.nextButt);

        next.setOnClickListener(v -> {
            int hp = 0;
            int character = 0; // just represents which char picked, carry over to game

            boolean diffSel = true;
            boolean charSel = true;
            boolean nameSel = true;

            RadioGroup diffs = findViewById(R.id.diffSelect);
            RadioGroup chars = findViewById(R.id.charSelect);

            int diffsCheckedRadioButtonId = diffs.getCheckedRadioButtonId();

            // pick difficulty
            System.out.println(R.id.easy);
            System.out.println();
            if (diffsCheckedRadioButtonId == R.id.easy) {
                hp = 100;
                diffSel = true;
            } else if (diffsCheckedRadioButtonId == R.id.medium) {
                hp = 50;
                diffSel = true;
            } else if (diffsCheckedRadioButtonId == R.id.hard) {
                hp = 30;
                diffSel = true;
            }

            int charsCheckedRadioButtonId = chars.getCheckedRadioButtonId();

            // pick character
            if (charsCheckedRadioButtonId == R.id.easy) {
                character = 1;
                charSel = true;
            } else if (charsCheckedRadioButtonId == R.id.medium) {
                character = 2;
                charSel = true;
            } else if (charsCheckedRadioButtonId == R.id.hard) {
                character = 3;
                charSel = true;
            }

            // todo: take in user input for name, check for edge cases
            TextInputLayout nameEntry = findViewById(R.id.nameEntry);
            String name = null;
            if (nameEntry.getEditText() != null && nameEntry.getEditText().getText() != null) {
                String temp = nameEntry.getEditText().getText().toString();
                if (temp.trim().length() > 0) {
                    nameSel = true;
                    name = nameEntry.getEditText().getText().toString();
                }
            }

            if (diffSel && charSel && nameSel) {
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("character", character);
                intent.putExtra("hp", hp);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}
