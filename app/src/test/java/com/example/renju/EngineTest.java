package com.example.renju;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.renju.logic.Board;
import com.example.renju.logic.Const;
import com.example.renju.logic.Engine;

import org.junit.Test;

public class EngineTest {

    @Test
    public void isValidBlackMove() {
        Engine engine = new Engine(8);
        Board board = engine.getBoard();

        board.getTile(1, 0).setPiece(Const.BLACK);
        board.getTile(2, 0).setPiece(Const.BLACK);
        board.getTile(0, 1).setPiece(Const.BLACK);
        board.getTile(0, 2).setPiece(Const.BLACK);
        assertFalse(engine.isValidBlackMove(0, 0));

        board.getTile(3, 0).setPiece(Const.BLACK);
        board.getTile(5, 0).setPiece(Const.BLACK);
        board.getTile(6, 0).setPiece(Const.BLACK);
        assertFalse(engine.isValidBlackMove(0, 4));
        assertTrue(engine.isValidBlackMove(0, 0));
        board.getTile(1, 1).setPiece(Const.BLACK);
        board.getTile(2, 2).setPiece(Const.BLACK);
        board.getTile(3, 3).setPiece(Const.BLACK);
        assertFalse(engine.isValidBlackMove(0, 0));
    }

}
