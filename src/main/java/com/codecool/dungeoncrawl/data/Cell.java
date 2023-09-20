package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.specialities.Speciality;
import com.codecool.dungeoncrawl.data.actors.Actor;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private Speciality speciality;
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
        int newXCoordinate = x + dx;
        int newYCoordinate = y + dy;

        if (newXCoordinate > 0 && newXCoordinate < gameMap.getWidth() &&
                newYCoordinate > 0 && newYCoordinate < gameMap.getHeight()) {
                return gameMap.getCell(newXCoordinate, newYCoordinate);
        }
        return null;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public boolean hasSpeciality() {
        return speciality != null;
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
