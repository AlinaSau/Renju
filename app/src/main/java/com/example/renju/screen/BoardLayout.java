package com.example.renju.screen;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.renju.logic.Board;

public class BoardLayout {

    TileView[][] tileViews;

    public BoardLayout(TableLayout tableLayout, Context context, Board board) {
        int boardSize = board.getSize();
        tileViews = new TileView[boardSize][boardSize];
        int width = Resources.getSystem().getDisplayMetrics().widthPixels / boardSize;
        TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, width);
        for (int i = 0; i < boardSize; i++) {
            TableRow tableRow = new TableRow(context);
            tableRow.setLayoutParams(rowParams);
            tableLayout.addView(tableRow);
            for (int j = 0; j < boardSize; j++) {
                tileViews[i][j] = new TileView(context, board.getTile(i, j));
                tileViews[i][j].setImageViewWidth(width);
                tableRow.addView(tileViews[i][j].getImageView());
            }
        }
    }


    public void setTileClickListeners(TileView.OnTileClickListener onTileClickListener) {
        for (int i = 0; i < tileViews.length; i++) {
            for (int j = 0; j < tileViews.length; j++) {
                tileViews[i][j].setOnTileClickListener(onTileClickListener);
            }
        }
    }

}

