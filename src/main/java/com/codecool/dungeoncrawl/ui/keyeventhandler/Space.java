package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.specialities.Gate;
import com.codecool.dungeoncrawl.data.specialities.WaterSwitcher;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Space implements KeyHandler {
    public static final KeyCode code = KeyCode.SPACE;

    @Override
    public void perform(KeyEvent event, GameMap map) {
        Cell playerCell = map.getPlayer().getCell();
        Cell cellUp = playerCell.getNeighbor(0, -1);
        Cell cellDown = playerCell.getNeighbor(0, 1);
        Cell cellRight = playerCell.getNeighbor(1, 0);
        Cell cellLeft = playerCell.getNeighbor(-1, 0);
        if (code.equals(event.getCode())) {
            changeNeighbourWallCellToFloor(map, cellUp, cellDown);
            changeNeighbourWallCellToFloor(map, cellRight, cellLeft);
            openGateWithKey(cellDown, map);
        }
    }

    private void openGateWithKey(Cell neighbourCell, GameMap map) {
        if (map.getPlayer().getInventory().playerHasKey()) {
            if (neighbourCell.getSpeciality().getTileName().equals("gateOff")) {
                ((Gate) neighbourCell.getSpeciality()).setOpened(true);
            }
        }
    }

    private void changeNeighbourWallCellToFloor(GameMap map, Cell cellUp, Cell cellDown) {
        if (cellUp.hasSpeciality() && cellUp.getType() == CellType.WALL) {
            cellUp.setType(CellType.FLOOR);
            checkSpeciality(cellUp, map);
        }
        if (cellDown.hasSpeciality() && cellDown.getType() == CellType.WALL) {
            cellDown.setType(CellType.FLOOR);
            checkSpeciality(cellDown, map);
        }
    }

    private void checkSpeciality(Cell neighbourCell, GameMap map) {
        if (neighbourCell.getSpeciality().getTileName().equals("waterSwitchOff")) {
            ((WaterSwitcher) neighbourCell.getSpeciality()).setOpened(true);
            map.checkMapForWaterSwitch();
        }
    }
}
