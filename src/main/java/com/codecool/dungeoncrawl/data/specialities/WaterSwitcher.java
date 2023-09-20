package com.codecool.dungeoncrawl.data.specialities;

import com.codecool.dungeoncrawl.data.Cell;

public class WaterSwitcher extends Speciality {
    public boolean isOpened = false;
    public WaterSwitcher(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        if (isOpened) {
            return "waterSwitchOn";
        } else {
            return "waterSwitchOff";
        }
    }
    public void setOpened(boolean opened) {
        isOpened = opened;
    }
}
