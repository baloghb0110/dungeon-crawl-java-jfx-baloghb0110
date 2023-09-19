package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Actor;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private GameMap gameMap;
    private int x, y;

    public Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    public List<Cell> getNeighbourCells(int dx, int dy) {
        List<Cell> neighbourCells = new ArrayList<>();
        neighbourCells.add(gameMap.getCell(x + dx, y + dy));
        neighbourCells.add(gameMap.getCell(x - dx, y - dy));
        neighbourCells.add(gameMap.getCell(x + dx, y - dy));
        neighbourCells.add(gameMap.getCell(x - dx, y + dy));
        return neighbourCells;
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
