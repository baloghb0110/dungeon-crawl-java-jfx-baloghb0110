package com.codecool.dungeoncrawl.data.specialities;

import com.codecool.dungeoncrawl.data.Cell;

public class Fire extends Speciality {
    public Fire(Cell cell) {
        super(cell);
    }
    @Override
    public String getTileName() { return "fire"; }
}
