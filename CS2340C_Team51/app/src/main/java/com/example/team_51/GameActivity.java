package com.example.team_51;

import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340c_team51.R;

public class GameActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        viewFlipper = findViewById(R.id.view_flipper);
    }

    public void endScreen(View v) {
        viewFlipper.showNext();
    }
}
