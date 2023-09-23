package com.example.team_51;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340c_team51.R;

public class GameActivity extends AppCompatActivity {
    private Button end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        end = (Button) findViewById(R.id.end);
    }

    public void endScreen(View v) {
        viewFlipper.showNext();
    }
}
