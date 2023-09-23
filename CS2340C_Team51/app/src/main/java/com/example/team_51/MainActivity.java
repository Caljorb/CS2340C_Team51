package com.example.team_51;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340c_team51.R;

public class MainActivity extends AppCompatActivity {

    private Button exit;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        //Button start = findViewById(R.id.{insert button id})
        // was looking at the example repo here to create this
        // https://github.com/CS-2340-Instructional-Team/android-lecture-activity/tree/main
        // kinda lost where to go from here rn, but it displays the start screen technically so :P

        // one thing to note: i think each screen requires its own "activity"
        // https://developer.android.com/guide/components/activities/intro-activities
        // use this w/ example repo to figure out how to make an activity

        exit=findViewById(R.id.Exit);
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
    }

    public void openInitConfigActivity() {
        Intent intent = new Intent(this, InitConfigActivity.class);
        startActivity(intent);
    }

    public void updateTe
}
