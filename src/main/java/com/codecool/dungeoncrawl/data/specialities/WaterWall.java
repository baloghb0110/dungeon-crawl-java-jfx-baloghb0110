package com.codecool.dungeoncrawl.data.specialities;

import com.codecool.dungeoncrawl.data.Cell;

public class WaterWall extends Speciality {
    public WaterWall(Cell cell) {
        super(cell);
    }
    public String getTileName() { return "waterWall"; }
}
