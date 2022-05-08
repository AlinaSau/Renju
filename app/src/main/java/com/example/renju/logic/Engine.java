package com.example.renju.logic;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Engine {


    public interface Listener {
        void onWin(int player);

        void onInvalidBlackMove();
    }

    private int turn = 0;
    private final Board board;
    private final Listener listener;


    public Engine(int size) {
        this(size, new Listener() {
            @Override
            public void onWin(int player) { }
            @Override
            public void onInvalidBlackMove() { }
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
        int player;
        if (turn % 2 == 0) player = Const.BLACK;
        else player = Const.WHITE;
        int max = board.maxInARow(x, y, player);
        if (player == Const.WHITE && max > 4)  listener.onWin(player);
        else if (player == Const.BLACK) {
            if (!isValidBlackMove(x, y)) {
                listener.onInvalidBlackMove();
                return;
            }
            else if (max == 5) listener.onWin(player);
        }
        board.getTile(y, x).setPiece(player);
        turn++;
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
        Log.i("Engine", first + "" + second + "" + third + "" + fourth);
        if (first > 5) return false;
        if (first < 3 || second < 3) return true;
        if (third > 2) return false;
        return first == 4 && second == 3;
    }

//    public boolean isWinTemplate(int dx, int dy, int player) {
//        int count = 0;
//        int row;
//        int column;
//
//        for (int i = 0; i < board.getSize(); i++) {
//            for (int j = 0; j < board.getSize(); j++) {
//                if (board.getTile(i, j).getOwner() == player) {
//                    Log.i("Engine", "i = " + i + "\nj =" + j);
//                    row = i;
//                    column = j;
//                    while (row >= 0 &&
//                            row < board.getSize() &&
//                            column >= 0 &&
//                            column < board.getSize() &&
//                            board.getTile(row, column).getOwner() == player) {
//                        Log.i("Engine", row + ":" + column + " count = " + count + " player = " + board.getTile(i, j).getOwner());
//                        count++;
//                        row += dy;
//                        column += dx;
//                    }
//                    if (count == 5) {
//                        Log.i("Engine", "return true");
//                        return true;
//                    } else {
//                        count = 0;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//    public boolean isHorizontalWin(int player) {
//        return isWinTemplate(1, 0, player);
//    }
//
//    public boolean isVerticalWin(int player) {
//        return isWinTemplate(0, 1, player);
//    }
//
//    public boolean isDiagonalWin(int player) {
//        return isWinTemplate(1, 1, player);
//    }
//
//    public boolean isWin(int player) {
//        return isDiagonalWin(player) || isHorizontalWin(player) || isVerticalWin(player);
//    }





}
