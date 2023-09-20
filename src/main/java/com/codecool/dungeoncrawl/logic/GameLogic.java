package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.data.specialities.Gate;
import com.codecool.dungeoncrawl.data.specialities.Speciality;
import com.codecool.dungeoncrawl.data.specialities.Water;
import com.codecool.dungeoncrawl.data.specialities.WaterSwitcher;
import com.codecool.dungeoncrawl.ui.UI;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private GameMap map;
    private List<Skeleton> skeletons;

    public GameLogic() {
        this.map = MapLoader.loadMap();
        this.skeletons = new ArrayList<>();
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

    public String getPlayerDamage() {
        return Integer.toString(map.getPlayer().getDamage());
    }

    public String getPlayerDefense() {
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
                if(elapsedSeconds < 1)
                    return;

                for (Skeleton skeleton : skeletons) {
                    skeleton.makeRandomMove();
                }

                map.releaseWater();
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
