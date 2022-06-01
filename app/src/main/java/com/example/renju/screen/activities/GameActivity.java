package com.example.renju.screen.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.renju.LeaderBoard;
import com.example.renju.R;
import com.example.renju.logic.Const;
import com.example.renju.logic.Engine;
import com.example.renju.logic.Tile;
import com.example.renju.screen.BoardLayout;
import com.example.renju.screen.TileView;

public class GameActivity extends AppCompatActivity {


    public static final String BOARD_SIZE = "board size";
    public static final String FIRST_PLAYER = "first player";
    public static final String SECOND_PLAYER = "second player";

    int boardSize;
    String firstPlayerName;
    String secondPlayerName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        boardSize = getIntent().getIntExtra(BOARD_SIZE, 8);
        firstPlayerName = getIntent().getStringExtra(FIRST_PLAYER);
        secondPlayerName = getIntent().getStringExtra(SECOND_PLAYER);
        TableLayout tableLayout = findViewById(R.id.table);
        Engine engine = new Engine(boardSize, new Engine.Listener() {
            @Override
            public void onWin(int player) {
                String winnerName;
                if (player == Const.WHITE) winnerName = secondPlayerName;
                else winnerName = firstPlayerName;
                LeaderBoard.getInstance(GameActivity.this).playerWon(winnerName);
                buildMessagePlayerWon(winnerName);
            }

            @Override
            public void onInvalidBlackMove() {
                Toast.makeText(GameActivity.this, "Black are bad", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void badMove() {
                Toast.makeText(GameActivity.this, "You can't make a move on this tile", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void boardIsOver() {
                new AlertDialog.Builder(GameActivity.this).setTitle("Board is over. This is the end of the game")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                onBackPressed();
                            }
                        })
                        .show();
            }
        });
        BoardLayout boardLayout = new BoardLayout(tableLayout, this, engine.getBoard());
        boardLayout.setTileClickListeners(new TileView.OnTileClickListener() {
            @Override
            public void onTileClick(Tile tile) {
                engine.processMove(tile.getX(), tile.getY());
            }
        });
    }

    private void buildMessagePlayerWon(String playerName) {
        new AlertDialog.Builder(this).setTitle("Player " + playerName + " won")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onBackPressed();
                    }
                })
                .show();
    }
}
