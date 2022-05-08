package com.example.renju;

import static org.junit.Assert.assertEquals;

import com.example.renju.logic.Board;
import com.example.renju.logic.Const;

import org.junit.Test;

public class BoardTest {

    @Test
    public void inHorizontalRow() {
        Board board = new Board(8);
        board.getTile(0, 0).setPiece(Const.WHITE);
        board.getTile(0, 1).setPiece(Const.WHITE);
        board.getTile(0, 2).setPiece(Const.WHITE);
        board.getTile(0, 3).setPiece(Const.WHITE);
        assertEquals(
                board.inHorizontalRow(0, 0, Const.WHITE),
                board.inHorizontalRow(1, 0, Const.WHITE)
        );
        assertEquals(board.inHorizontalRow(0, 0, Const.WHITE), 4);
        assertEquals(
                board.inHorizontalRow(0, 0, Const.WHITE),
                board.inHorizontalRow(3, 0, Const.WHITE)
        );
    }

    @Test
    public void inVerticalRow() {
        Board board = new Board(8);
        board.getTile(0, 0).setPiece(Const.WHITE);
        board.getTile(1, 0).setPiece(Const.WHITE);
        board.getTile(2, 0).setPiece(Const.WHITE);
        board.getTile(3, 0).setPiece(Const.WHITE);
        assertEquals(
                board.inVerticalRow(0, 0, Const.WHITE),
                board.inVerticalRow(0, 1, Const.WHITE)
        );
        assertEquals(board.inVerticalRow(0, 0, Const.WHITE), 4);
        assertEquals(
                board.inVerticalRow(0, 0, Const.WHITE),
                board.inVerticalRow(0, 3, Const.WHITE)
        );
    }
    @Test
    public void inMainDiagonalRow() {
        Board board = new Board(8);
        board.getTile(0, 0).setPiece(Const.WHITE);
        board.getTile(1, 1).setPiece(Const.WHITE);
        board.getTile(2, 2).setPiece(Const.WHITE);
        board.getTile(3, 3).setPiece(Const.WHITE);
        assertEquals(
                board.inMainDiagonalRow(0, 0, Const.WHITE),
                board.inMainDiagonalRow(1, 1, Const.WHITE)
        );
        assertEquals(board.inMainDiagonalRow(0, 0, Const.WHITE), 4);
        assertEquals(
                board.inMainDiagonalRow(0, 0, Const.WHITE),
                board.inMainDiagonalRow(3, 3, Const.WHITE)
        );
    }
    @Test
    public void inSideDiagonalRow() {
        Board board = new Board(8);
        board.getTile(0, 3).setPiece(Const.WHITE);
        board.getTile(1, 2).setPiece(Const.WHITE);
        board.getTile(2, 1).setPiece(Const.WHITE);
        board.getTile(3, 0).setPiece(Const.WHITE);
        assertEquals(
                board.inSideDiagonalRow(0, 3, Const.WHITE),
                board.inSideDiagonalRow(1, 2, Const.WHITE)
        );
        assertEquals(board.inSideDiagonalRow(0, 3, Const.WHITE), 4);
        assertEquals(
                board.inSideDiagonalRow(0, 3, Const.WHITE),
                board.inSideDiagonalRow(3, 0, Const.WHITE)
        );
    }
    @Test
    public void maxInARow() {
        Board board = new Board(8);
        board.getTile(4, 2).setPiece(Const.WHITE);
        board.getTile(4, 3).setPiece(Const.WHITE);
        board.getTile(4, 4).setPiece(Const.WHITE);
        board.getTile(4, 5).setPiece(Const.WHITE);
        board.getTile(5, 4).setPiece(Const.WHITE);
        board.getTile(6, 4).setPiece(Const.WHITE);
        board.getTile(7, 4).setPiece(Const.WHITE);
        board.getTile(5, 5).setPiece(Const.WHITE);
        assertEquals(board.maxInARow(4, 4, Const.WHITE), 4);
    }

}
