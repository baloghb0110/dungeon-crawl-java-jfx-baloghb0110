package com.codecool.dungeoncrawl.data.specialities;

import com.codecool.dungeoncrawl.data.Cell;

public class WaterGate extends Speciality {
    public WaterGate(Cell cell) {
        super(cell);
    }
    public String getTileName() { return "waterGate"; }
}
