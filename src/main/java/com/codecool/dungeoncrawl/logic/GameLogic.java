package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.data.artifacts.Inventory;
import com.codecool.dungeoncrawl.data.artifacts.Item;
import com.codecool.dungeoncrawl.ui.UI;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private static GameLogic instance;
    private GameMap map;
    private Player player;
    private Cell currentCell;
    private List<Skeleton> skeletons;
    private Inventory inventory;

    GameLogic() {
        this.map = MapLoader.loadMap();
        this.skeletons = new ArrayList<>();
        this.inventory = new Inventory();
        findSkeletons();
    }

    public static GameLogic getInstance() {
        if (instance == null) {
            instance = new GameLogic();
        }
        return instance;
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

    public Cell getCurrentCell() {
        return currentCell;
    }

    public String getPlayerHealthLabel() {
        return Integer.toString(map.getPlayer().getHealth());
    }

    public int getPlayerHealth() {
        return map.getPlayer().getHealth();
    }


    public void increaseHealth(int healthBoost) {
        int currentHealth = getPlayerHealth();
        map.getPlayer().setHealth(currentHealth+healthBoost);
    }

    public void increaseAttack(int atkBoost) {
        int currentAtk = getPlayerAttack();
        map.getPlayer().setDamage(currentAtk+atkBoost);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void updateCurrentCell() {
        int playerX = map.getPlayer().getX();
        int playerY = map.getPlayer().getY();
        this.currentCell = map.getCell(playerX, playerY);
    }

    public void addItemToInventory() {
        updateCurrentCell();

        if (currentCell.getItem() != null) {
            System.out.println("item found");

            String itemName = currentCell.getItem().getName();

            if (!itemName.equals("life")) {
                addItemToInventoryHelper(itemName);
            }

            if (itemName.equals("life")) {
                System.out.println("why are we in this method in the first place?");
                ;
            }

            removeItemFromMap(itemName);
        }
    }

    private void addItemToInventoryHelper(String itemName) {
        inventory.addItem(currentCell.getItem());

        System.out.println("item added to inventory: " + itemName);

        printInventory();

    }

    private void printInventory() {
        System.out.println("inventory contents: ");
        for (Item item : inventory.getItems()) {
            System.out.println("- " + item.getName());
        }
    }

    private void removeItemFromMap(String itemName) {
        currentCell.setItem(null);
        System.out.println("item removed from map: " + itemName);
    }

    public void removeItemFromMap() {
        currentCell.setItem(null);
    }

    public String getPlayerAttackLabel() {
        return Integer.toString(map.getPlayer().getDamage());
    }

    public int getPlayerAttack() {
        return map.getPlayer().getDamage();
    }

    public String getPlayerDefenseLabel() {
        return Integer.toString(map.getPlayer().getDefense());
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
