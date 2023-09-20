package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

public abstract class Item implements Drawable {
    private Cell cell;
    private String name;

    public Item(Cell cell, String name) {
        this.cell = cell;
        this.cell.setItem(this);
        this.name = name;
    }

    public Cell getCell() {
        return cell;
    }

    public String getName() { return name; }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
