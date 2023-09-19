package com.codecool.dungeoncrawl.data.Specialities;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;

public class WaterWall extends Speciality {
    public WaterWall(Cell cell) {
        super(cell);
    }
    public String getTileName() { return "waterWall"; }
}
