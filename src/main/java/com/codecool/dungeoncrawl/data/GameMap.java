package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.specialities.Gate;
import com.codecool.dungeoncrawl.data.specialities.Speciality;
import com.codecool.dungeoncrawl.data.specialities.Water;
import com.codecool.dungeoncrawl.data.specialities.WaterGate;
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

    public Cell findOpenedWaterGate() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                Cell cell = getCell(x, y);
                if (cell.hasSpeciality()) {
                    Speciality speciality = cell.getSpeciality();
                    if (speciality.getTileName().equals("waterGateOpen")) {
                        return cell;
                    }
                }
            }
        }
        return null;
    }

    public void findAndOpenWaterGate() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                Cell cell = getCell(x, y);
                if (cell.hasSpeciality()) {
                    Speciality speciality = cell.getSpeciality();
                    if (speciality.getTileName().equals("waterSwitchOn")) {
                        for (int j = 0; j < getWidth(); j++) {
                            for (int h = 0; h < getHeight(); h++) {
                                Cell cell1 = getCell(j, h);
                                if (cell1.hasSpeciality()) {
                                    Speciality speciality1 = cell1.getSpeciality();
                                    if (speciality1.getTileName().equals("waterGateClosed")) {
                                        ((WaterGate) speciality1).setOpened(true);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
