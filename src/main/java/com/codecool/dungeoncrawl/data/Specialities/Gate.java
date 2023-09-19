package com.codecool.dungeoncrawl.data.Specialities;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;

public class Gate extends Speciality {
    public Gate(Cell cell) {
        super(cell);
    }
    public String getTileName() { return "gate"; }
}
