package com.codecool.dungeoncrawl.data.Specialities;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;

public class WaterGate extends Speciality {
    public WaterGate(Cell cell) {
        super(cell);
    }
    public String getTileName() { return "waterGate"; }
}
