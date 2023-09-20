package com.codecool.dungeoncrawl.data.specialities;

import com.codecool.dungeoncrawl.data.Cell;

public class RunningWater extends Speciality {
    public RunningWater(Cell cell) {
        super(cell);
    }
    public String getTileName() { return "runningWater"; }
}
