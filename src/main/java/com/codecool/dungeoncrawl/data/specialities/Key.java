package com.codecool.dungeoncrawl.data.specialities;

import com.codecool.dungeoncrawl.data.Cell;

public class Key extends Speciality {
    public Key(Cell cell) {
        super(cell);
    }
    public String getTileName() { return "key"; }
}
