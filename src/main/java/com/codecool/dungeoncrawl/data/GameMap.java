package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.specialities.RunningWater;
import com.codecool.dungeoncrawl.data.specialities.Speciality;
import com.codecool.dungeoncrawl.data.specialities.Water;
import com.codecool.dungeoncrawl.data.specialities.WaterGate;
import com.codecool.dungeoncrawl.data.actors.Skeleton;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;
    private List<Skeleton> skeletonList;
    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        this.skeletonList = new ArrayList<>();
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
            Cell currentCell;
            do {
                currentCell = waterGateCell.getNeighbor(0, counter++);
                new RunningWater(currentCell);
            }
            while (currentCell.getNeighbor(0, 1).getTileName().equals("floor"));
            Cell downNeighbourCell = currentCell.getNeighbor(0, 1);

            downNeighbourCell.setType(CellType.FLOOR);
            downNeighbourCell.setSpeciality(null);
            ((WaterGate) waterGateCell.getSpeciality()).setOpened(false);
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

    private void checkIfWaterSwitchOn(Cell cell) {
        if (cell.hasSpeciality()) {
            Speciality speciality = cell.getSpeciality();
            if (speciality.getTileName().equals("waterSwitchOn")) {
                checkMapForWaterGate();
            }
        }
    }

    private void checkMapForWaterGate() {
        for (int j = 0; j < getWidth(); j++) {
            for (int h = 0; h < getHeight(); h++) {
                Cell cell1 = getCell(j, h);
                openWaterGate(cell1);
            }
        }
    }

    private void openWaterGate(Cell cell) {
        if (cell.hasSpeciality()) {
            Speciality speciality = cell.getSpeciality();
            if (speciality.getTileName().equals("waterGateClosed")) {
                ((WaterGate) speciality).setOpened(true);
            }
        }

    public void findSkeletons() {
        skeletonList = new ArrayList<>();
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                Cell cell = this.getCell(x, y);
                Actor actor = cell.getActor();
                if (actor instanceof Skeleton) {
                    skeletonList.add((Skeleton) actor);
                }
            }
        }
    }

    public List<Skeleton> getSkeletonList(){
        return new ArrayList<>(skeletonList);
    }
}
