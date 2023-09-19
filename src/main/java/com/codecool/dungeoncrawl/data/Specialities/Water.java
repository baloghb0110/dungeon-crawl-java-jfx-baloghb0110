package com.codecool.dungeoncrawl.data.Specialities;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;

public class Water extends Speciality {

    public Water(Cell cell) {
        super(cell);
    }

    public String getTileName() { return "water"; }
}
