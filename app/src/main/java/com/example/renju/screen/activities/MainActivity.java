package com.example.renju.screen.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.renju.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startGameButton = findViewById(R.id.start_game_btn);
        startGameButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GameSettingsActivity.class);
            startActivity(intent);
        });

        Button leaderBoardButton = findViewById(R.id.leaderboard_btn);
        leaderBoardButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LeaderBoardActivity.class);
            startActivity(intent);
        });
    }
}