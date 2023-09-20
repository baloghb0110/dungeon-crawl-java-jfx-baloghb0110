package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Cica extends Actor {
    public Cica(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "cica";
    }
}