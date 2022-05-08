package com.example.renju.logic;

public class Board {

    private Tile[][] tiles;
    private int size;

    Board(int size) {
        this.size = size;
        tiles = new Tile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles[i][j] = new Tile(j, i);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public Tile getTile(int i, int j) {
        return tiles[i][j];
    }


    public int inARowTemplate(int x,
                              int y,
                              int dx,
                              int dy,
                              int player) {
        int count = 0;
        int currentX = x + dx;
        int currentY = y + dy;
        while (0 <= currentX && currentX < size &&
                0 <= currentY && currentY < size &&
                tiles[currentY][currentX].getOwner() == player) {
            count++;
            currentX += dx;
            currentY += dy;
        }
        currentX = x - dx;
        currentY = y - dy;
        while (0 <= currentX && currentX < size &&
                0 <= currentY && currentY < size &&
                tiles[currentY][currentX].getOwner() == player) {
            count++;
            currentX -= dx;
            currentY -= dy;
        }
        return count + 1;
    }

    public int inHorizontalRow(int x, int y, int player) {
        return inARowTemplate(x, y, 1, 0, player);
    }

    public int inVerticalRow(int x, int y, int player) {
        return inARowTemplate(x, y, 0, 1, player);
    }

    public int inMainDiagonalRow(int x, int y, int player) {
        return inARowTemplate(x, y, 1, 1, player);
    }

    public int inSideDiagonalRow(int x, int y, int player) {
        return inARowTemplate(x, y, -1, 1, player);
    }

    public int maxInARow(int x, int y, int player) {
        return Math.max(
                Math.max(
                        inHorizontalRow(x, y, player),
                        inVerticalRow(x, y, player)
                ),
                Math.max(
                        inMainDiagonalRow(x, y, player),
                        inSideDiagonalRow(x, y, player)
                )
        );
    }


}
