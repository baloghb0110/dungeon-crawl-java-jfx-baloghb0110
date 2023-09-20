package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public class Portal extends Item {

    public Portal(Cell cell) {
        super(cell, "portal");
    }

    @Override
    public String getTileName() {
        return "portal";
    }
}