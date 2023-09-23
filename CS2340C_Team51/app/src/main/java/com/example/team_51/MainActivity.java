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


    private Button button;
    private ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        viewFlipper = findViewById(R.id.view_flipper);
    }
    public void endScreen(View v) {
        viewFlipper.showNext();

        /*exit=findViewById(R.id.Exit);
        exit.setOnClickListener(new_View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_end_screen.xml);
                startActivity(intent);
            }
        }

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInitConfigActivity();
            }
        });
>>>>>>> config
    }

    public void openInitConfigActivity() {
        Intent intent = new Intent(this, InitConfigActivity.class);
        startActivity(intent);
    }

    public void updateTe*/
    }
}
