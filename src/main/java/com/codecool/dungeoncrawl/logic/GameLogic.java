package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.data.specialities.Gate;
import com.codecool.dungeoncrawl.data.specialities.Speciality;
import com.codecool.dungeoncrawl.data.specialities.Water;
import com.codecool.dungeoncrawl.data.specialities.WaterSwitcher;
import com.codecool.dungeoncrawl.data.artifacts.Inventory;
import com.codecool.dungeoncrawl.data.artifacts.Item;
import com.codecool.dungeoncrawl.ui.UI;
import javafx.animation.AnimationTimer;

import java.util.List;


public class GameLogic {
    private static GameLogic instance;
    private GameMap map;
    private Player player;
    private Cell currentCell;
    private Inventory inventory;

    GameLogic() {
        this.map = MapLoader.loadMap();
        this.inventory = map.getPlayer().getInventory();
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


    private boolean levelEnd;

    public void setLevelEnd() {
        boolean hasKey;
        List<Item> items = inventory.getItems();

        if (inventory.playerHasKey()) {
            System.out.println("level cleared");
        }
    }

    public int getPlayerHealth() {
        return map.getPlayer().getHealth();
    }


    public void increaseHealth(int healthBoost) {
        int currentHealth = getPlayerHealth();
        map.getPlayer().setHealth(currentHealth + healthBoost);
    }

    public void increaseAttack(int atkBoost) {
        int currentAtk = getPlayerAttack();
        map.getPlayer().setDamage(currentAtk + atkBoost);
    }

    public void increaseDefense(int defBoost) {
        int currentDef = getPlayerDefense();
        map.getPlayer().setDefense(currentDef + defBoost);
    }


    public void updateCurrentCell() {
        int playerX = map.getPlayer().getX();
        int playerY = map.getPlayer().getY();
        this.currentCell = map.getCell(playerX, playerY);
    }

    public void addItemToInventory() {
        updateCurrentCell();

        if (currentCell.getItem() != null) {
            String itemName = currentCell.getItem().getName();

            if (!itemName.equals("life")) {
                addItemToInventoryHelper(itemName);
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

    public String getPlayerInventoryLabel() {
        List<Item> items = map.getPlayer().getInventory().getItems();
        StringBuilder inv = new StringBuilder();
        for (Item item :
                items) {
            inv.append("- ").append(item.getName()).append("\n");
        }
        return inv.toString();
    }

    public int getPlayerDefense() {
        return map.getPlayer().getDefense();
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
                if(map.getPlayer().getHealth() <= 0) {
                    GameOverScreen.display("Game Over!");
                    this.stop();
                }

                map.findSkeletons();

                for (Skeleton skeleton : getMap().getSkeletonList()) {
                    if (isPlayerInRange(map.getPlayer(), skeleton)) {
                        skeleton.chasePlayer(map.getPlayer());
                    } else {
                        skeleton.makeRandomMove();
                    }
                }

                map.releaseWater();
                ui.refresh();
                lastUpdate = now;
            }
        }.start();
    }

    private boolean isPlayerInRange(Player player, Skeleton skeleton) {
        int dx = Math.abs(player.getX() - skeleton.getX());
        int dy = Math.abs(player.getY() - skeleton.getY());

        int chaseRange = 5;

        return dx <= chaseRange && dy <= chaseRange;
    }
}
