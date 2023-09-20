package com.codecool.dungeoncrawl.data.specialities;

import com.codecool.dungeoncrawl.data.Cell;

public class WaterGate extends Speciality {
    public boolean isOpened = false;
    public WaterGate(Cell cell) {
        super(cell);
    }
    @Override
    public String getTileName() {
        if (isOpened) {
            return "waterGateOpen";
        } else {
            return "waterGateClosed";
        }
    }
    public void setOpened(boolean opened) {
        isOpened = opened;
    }
}
