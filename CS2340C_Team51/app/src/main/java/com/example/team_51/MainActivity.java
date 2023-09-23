package com.example.team_51;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340c_team51.R;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

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
}
