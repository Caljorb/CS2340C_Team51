package com.example.team_51;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340c_team51.R;

import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {


    private Button start;
    private Button quit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

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
        startActivity(intent);
    }

}
