package com.example.renju.screen.activities;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.renju.LeaderBoard;
import com.example.renju.R;

public class LeaderBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        ListView listView = findViewById(R.id.leaderboard);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, LeaderBoard.getInstance(this).getLeaderBoardList()
        );
        listView.setAdapter(adapter);
    }


}
