package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.data.items.Inventory;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.ui.UI;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private GameMap map;
    private List<Skeleton> skeletons;
    private Inventory inventory;

    public GameLogic() {
        this.map = MapLoader.loadMap();
        this.skeletons = new ArrayList<>();
        this.inventory = new Inventory();
        findSkeletons();
    }

    public double getMapWidth() {
        return map.getWidth();
    }

    public double getMapHeight() {
        return map.getHeight();
    }

    public void setup() {
    }

    public Cell getCell(int x, int y) {
        return map.getCell(x, y);
    }

    public String getPlayerHealth() {
        return Integer.toString(map.getPlayer().getHealth());
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addItemToInventory() {
        int playerX = map.getPlayer().getX();
        int playerY = map.getPlayer().getY();

        Cell currentCell = map.getCell(playerX, playerY);

        if (currentCell.getItem() != null) {
            System.out.println("item found");

            String itemName = currentCell.getItem().getName();

            // add to inventory
            inventory.addItem(currentCell.getItem());

            System.out.println("item added to inventory: " + itemName);

            // print inventory
            System.out.println("inventory contents: ");
            for (Item item : inventory.getItems()) {
                System.out.println(item.getName());
            }

            // remove item from map
            currentCell.setItem(null);
            System.out.println("item removed from map: " + itemName);
        }
    }

    public GameMap getMap() {
        return map;
    }

    public void startGameLoop(UI ui) {
        new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                double elapsedSeconds = (now - lastUpdate) / 1_000_000_000.0;
                if (elapsedSeconds < 1) return;

                for (Skeleton skeleton : skeletons) {
                    skeleton.makeRandomMove();
                }

                ui.refresh();
                lastUpdate = now;
            }
        }.start();
    }

    private void findSkeletons() {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                Actor actor = cell.getActor();
                if (actor instanceof Skeleton) {
                    skeletons.add((Skeleton) actor);
                }
            }
        }
    }
}
