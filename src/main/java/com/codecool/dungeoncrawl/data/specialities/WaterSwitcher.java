package com.codecool.dungeoncrawl.data.specialities;

import com.codecool.dungeoncrawl.data.Cell;

public class WaterSwitcher extends Speciality {
    public WaterSwitcher(Cell cell) {
        super(cell);
    }
    public String getTileName() { return "waterSwitchOff"; }
}
