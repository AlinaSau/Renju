package com.example.renju.screen.activities;

import static com.example.renju.screen.activities.GameActivity.BOARD_SIZE;
import static com.example.renju.screen.activities.GameActivity.FIRST_PLAYER;
import static com.example.renju.screen.activities.GameActivity.SECOND_PLAYER;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.renju.R;

public class GameSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);
        Button nextActivityButton = findViewById(R.id.next_activity);
        EditText firstPlayerNameEditText = findViewById(R.id.player1_et);
        EditText secondPlayerNameEditText = findViewById(R.id.player2_et);
        EditText boardSize = findViewById(R.id.board_size);


        nextActivityButton.setOnClickListener(view -> {
            Intent intent = new Intent(GameSettingsActivity.this, GameActivity.class);

            if (!correctEditTexts(firstPlayerNameEditText, secondPlayerNameEditText, boardSize)) return;
            intent.putExtra(BOARD_SIZE, Integer.parseInt(boardSize.getText().toString()));
            intent.putExtra(FIRST_PLAYER, firstPlayerNameEditText.getText().toString());
            intent.putExtra(SECOND_PLAYER, secondPlayerNameEditText.getText().toString());
            startActivity(intent);
        });
    }
    
    private boolean correctEditTexts(EditText firstName, EditText secondName, EditText boardSize) {
        String firstText = firstName.getText().toString();
        String secondText = secondName.getText().toString();
        String size = boardSize.getText().toString();
        return !firstText.isEmpty() && !secondText.isEmpty() && !firstText.equals(secondText) && !size.isEmpty();
    }



}
