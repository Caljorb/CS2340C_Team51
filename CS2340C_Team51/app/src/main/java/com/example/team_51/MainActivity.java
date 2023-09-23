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
    @Override

    private Button button;
    private ViewFlipper viewFlipper;

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
        viewFlipper = findViewById(R.id.view_flipper);
    }
    public void endScreen(View v) {
        viewFlipper.showNext();
    }

    public void openInitConfigActivity() {
        Intent intent = new Intent(this, InitConfigActivity.class);
        startActivity(intent);
    }

    public void updateTe
}
