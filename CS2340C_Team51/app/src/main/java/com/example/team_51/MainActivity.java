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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

}
