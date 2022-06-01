package com.example.renju.logic;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.renju.screen.activities.GameActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Engine {


    public interface Listener {
        void onWin(int player);

        void onInvalidBlackMove();

        void badMove();

        void boardIsOver();
    }

    private int turn = 0;
    private final Board board;
    private final Listener listener;


    public Engine(int size) {
        this(size, new Listener() {
            @Override
            public void onWin(int player) {
            }

            @Override
            public void onInvalidBlackMove() {
            }

            @Override
            public void badMove() {
            }
            @Override
            public void boardIsOver() {
            }
        });
    }

    public Engine(int size, Listener listener) {
        board = new Board(size);
        this.listener = listener;
    }


    public Board getBoard() {
        return board;
    }

    public void processMove(int x, int y) {
        int player = Const.EMPTY;
        if (moveToEmptyTile(x, y)) {
            if (turn % 2 == 0)
                player = Const.BLACK;
            else player = Const.WHITE;
            turn++;
        } else listener.badMove();
        if (player != Const.EMPTY) {
            int max = board.maxInARow(x, y, player);
            if (player == Const.WHITE && max > 4) listener.onWin(player);
            else if (player == Const.BLACK) {
                if (!isValidBlackMove(x, y)) {
                    listener.onInvalidBlackMove();
                    turn--;
                    return;
                } else if (max == 5) listener.onWin(player);
            }
            board.getTile(y, x).setPiece(player);
        }
        if (turn == board.getSize()* board.getSize()) listener.boardIsOver();
    }

    public boolean moveToEmptyTile (int x, int y) {
        if (board.getTile(y, x).getOwner() != Const.BLACK && board.getTile(y, x).getOwner() != Const.WHITE)
            return true;
        else {
            listener.badMove();
            return false;
        }
    }

    public boolean isValidBlackMove(int x, int y) {
        List<Integer> piecesOnRows = new ArrayList<>();
        piecesOnRows.add(board.inHorizontalRow(x, y, Const.BLACK));
        piecesOnRows.add(board.inVerticalRow(x, y, Const.BLACK));
        piecesOnRows.add(board.inMainDiagonalRow(x, y, Const.BLACK));
        piecesOnRows.add(board.inSideDiagonalRow(x, y, Const.BLACK));
        Collections.sort(piecesOnRows, Collections.reverseOrder());
        int first = piecesOnRows.get(0);
        int second = piecesOnRows.get(1);
        int third = piecesOnRows.get(2);
        int fourth = piecesOnRows.get(3);
        if (first > 5) return false;
        if (first < 3 || second < 3) return true;
        if (third > 2) return false;
        return first == 4 && second == 3;
    }
}
