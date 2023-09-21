package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.artifacts.Inventory;
import com.codecool.dungeoncrawl.data.artifacts.Item;

import java.util.List;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
        this.inventory = new Inventory();
    }

    private Inventory inventory;

    public String getTileName() {
        return "player";
    }

    public Inventory getInventory() {
        return inventory;
    }

}
