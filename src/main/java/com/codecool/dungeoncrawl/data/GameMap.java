package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.logic.Game;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isCanMove(int x, int y, GameMap map){
        int playerX = map.getPlayer().getX();
        int playerY = map.getPlayer().getY();

        Cell currentCell = map.getCell(playerX, playerY);
        Cell nextCell = currentCell.getNeighbor(x, y);

        if(nextCell.getType() == CellType.FLOOR && nextCell.getActor() == null)
            return true;
        return false;
    }
}
