package com.example.renju.logic;

public class Tile {


    private final int x;
    private final int y;
    private int owner;
    private Observer observer;

    Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPiece(int player) {
        owner = player;
        if (observer != null) observer.onSetPiece(player);
    }

    public int getOwner() {
        return owner;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public interface Observer {
        void onSetPiece(int color);
    }

}
