package com.codecool.dungeoncrawl.data.specialities;

import com.codecool.dungeoncrawl.data.Cell;

public class Water extends Speciality {
    public Water(Cell cell) {
        super(cell);
    }
    public String getTileName() { return "water"; }
}
