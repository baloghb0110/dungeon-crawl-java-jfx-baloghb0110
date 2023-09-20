package com.codecool.dungeoncrawl.data.specialities;

import com.codecool.dungeoncrawl.data.Cell;

public class Gate extends Speciality {
    public boolean isOpened = false;
    public Gate(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        if (isOpened) {
            return "gateOn";
        } else {
            return "gateOff";
        }
    }
    public void setOpened(boolean opened) {
        isOpened = opened;
    }
}
