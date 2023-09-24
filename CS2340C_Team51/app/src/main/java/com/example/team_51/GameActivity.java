package com.example.team_51;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340c_team51.R;

public class GameActivity extends AppCompatActivity {
    private Button end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);



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
