package com.codecool.dungeoncrawl.data.Specialities;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;

public class RunningWater extends Speciality {
    public RunningWater(Cell cell) {
        super(cell);
    }
    public String getTileName() { return "runningWater"; }
}
