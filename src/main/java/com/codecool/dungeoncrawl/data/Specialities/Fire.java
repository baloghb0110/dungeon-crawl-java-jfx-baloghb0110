package com.codecool.dungeoncrawl.data.Specialities;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;

public class Fire extends Speciality {
    public Fire(Cell cell) {
        super(cell);
    }
    public String getTileName() { return "fire"; }
}
