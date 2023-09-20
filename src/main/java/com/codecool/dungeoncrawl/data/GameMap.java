package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.specialities.RunningWater;
import com.codecool.dungeoncrawl.data.specialities.Speciality;
import com.codecool.dungeoncrawl.data.specialities.Water;
import com.codecool.dungeoncrawl.data.specialities.WaterGate;

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

    public void releaseWater() {
        int counter = 1;
        Cell waterGateCell = findOpenedWaterGate();
        if (waterGateCell != null) {
            Cell currentCell = waterGateCell.getNeighbor(0, counter);

            while (currentCell != null && !currentCell.getTileName().equals("fire")) {
                RunningWater runningWater = new RunningWater(currentCell);
                currentCell.setSpeciality(runningWater);

                currentCell = currentCell.getNeighbor(0, counter);
                counter++;
            }
            if (currentCell != null && currentCell.getTileName().equals("fire")) {
                do {
                    currentCell.setSpeciality(null);
                    currentCell = currentCell.getNeighbor(0, counter);
                    //counter--;
                }
                while (currentCell.getType() == CellType.WALL);
            }

            //new RunningWater(currentCell);
        }
    }

    public void checkMapForWaterSwitch() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                Cell cell = getCell(x, y);
                checkIfWaterSwitchOn(cell);
            }
        }
    }

    public void checkIfWaterSwitchOn(Cell cell) {
        if (cell.hasSpeciality()) {
            Speciality speciality = cell.getSpeciality();
            if (speciality.getTileName().equals("waterSwitchOn")) {
                checkMapForWaterGate();
            }
        }
    }

    public void checkMapForWaterGate() {
        for (int j = 0; j < getWidth(); j++) {
            for (int h = 0; h < getHeight(); h++) {
                Cell cell1 = getCell(j, h);
                openWaterGate(cell1);
            }
        }
    }

    public void openWaterGate(Cell cell) {
        if (cell.hasSpeciality()) {
            Speciality speciality1 = cell.getSpeciality();
            if (speciality1.getTileName().equals("waterGateClosed")) {
                ((WaterGate) speciality1).setOpened(true);
            }
        }
    }
}
