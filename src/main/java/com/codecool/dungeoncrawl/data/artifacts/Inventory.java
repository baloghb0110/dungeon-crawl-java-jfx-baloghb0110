package com.codecool.dungeoncrawl.data.artifacts;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Item> inventory;

    public Inventory() {
        this.inventory = new ArrayList<>();
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public List<Item> getItems() {
        return new ArrayList<>(inventory);
    }

    public boolean playerHasKey() {
        return inventory.stream().anyMatch(item -> item.getName().equals("key"));
    }
}