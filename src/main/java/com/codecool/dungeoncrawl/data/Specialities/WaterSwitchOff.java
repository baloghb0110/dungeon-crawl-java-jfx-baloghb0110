package com.codecool.dungeoncrawl.data.Specialities;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;

public class WaterSwitchOff extends Speciality {
    public WaterSwitchOff(Cell cell) {
        super(cell);
    }

    public String getTileName() { return "waterSwitchOff"; }
}
