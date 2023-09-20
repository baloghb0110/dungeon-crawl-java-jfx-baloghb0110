package com.codecool.dungeoncrawl.data.specialities;

import com.codecool.dungeoncrawl.data.Cell;

public class Gate extends Speciality {
    public Gate(Cell cell) {
        super(cell);
    }
    public String getTileName() { return "gate"; }
}
