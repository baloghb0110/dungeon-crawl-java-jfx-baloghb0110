package com.codecool.dungeoncrawl.data.Specialities;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;

public class WaterSwitchOn extends Speciality {

    public WaterSwitchOn(Cell cell) {
        super(cell);
    }

    public String getTileName() { return "waterSwitchOn"; }
}
