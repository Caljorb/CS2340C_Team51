package com.example.team_51;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340c_team51.R;

public class GameActivity extends AppCompatActivity {
    private Button end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        Intent intent = getIntent();

        if (intent != null) {
            int charsCheckedRadioButtonId = intent.getIntExtra("charsCheckedRadioButtonId", 1);
            ImageView char1 = findViewById(R.id.char1);
            ImageView char2 = findViewById(R.id.char2);
            ImageView char3 = findViewById(R.id.char3);

            if (charsCheckedRadioButtonId == (int) R.id.charSel1) {
                char1.setVisibility(View.VISIBLE);
                char2.setVisibility(View.GONE);
                char3.setVisibility(View.GONE);
            } else if (charsCheckedRadioButtonId == (int) R.id.charSel2) {
                char2.setVisibility(View.VISIBLE);
                char1.setVisibility(View.GONE);
                char3.setVisibility(View.GONE);
            } else if (charsCheckedRadioButtonId == (int) R.id.charSel3) {
                char3.setVisibility(View.VISIBLE);
                char1.setVisibility(View.GONE);
                char2.setVisibility(View.GONE);
            }

            int diffsCheckedRadioButtonId = intent.getIntExtra("diffsCheckedRadioButtonId", 1);
            TextView hp1 = findViewById(R.id.hp1);
            TextView diff1 = findViewById(R.id.diffEasy);
            TextView hp2 = findViewById(R.id.hp2);
            TextView diff2 = findViewById(R.id.diffMedium);
            TextView hp3 = findViewById(R.id.hp3);
            TextView diff3 = findViewById(R.id.diffHard);

            if (diffsCheckedRadioButtonId == R.id.easy) {
                hp1.setVisibility(View.VISIBLE);
                diff1.setVisibility(View.VISIBLE);

            } else if (diffsCheckedRadioButtonId == R.id.medium) {
                hp2.setVisibility(View.VISIBLE);
                diff2.setVisibility(View.VISIBLE);
            } else {
                hp3.setVisibility(View.VISIBLE);
                diff3.setVisibility(View.VISIBLE);
            }


        }

        end = (Button) findViewById(R.id.end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEndScreen();
            }
        });
    }

    public void openEndScreen() {
        Intent intent = new Intent(this, EndActivity.class);
        startActivity(intent);
    }


}
