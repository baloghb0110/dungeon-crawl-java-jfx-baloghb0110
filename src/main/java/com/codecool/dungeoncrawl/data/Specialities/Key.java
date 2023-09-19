package com.codecool.dungeoncrawl.data.Specialities;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;

public class Key extends Speciality {

    public Key(Cell cell) {
        super(cell);
    }

    public String getTileName() { return "key"; }
}
