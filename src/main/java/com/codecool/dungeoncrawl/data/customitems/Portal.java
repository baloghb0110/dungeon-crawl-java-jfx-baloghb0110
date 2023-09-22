package com.codecool.dungeoncrawl.data.customitems;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.artifacts.Item;
import com.codecool.dungeoncrawl.logic.GameLogic;

public class Portal extends Item {

    public Portal(Cell cell) {
        super(cell, "portal");
    }

    @Override
    public String getTileName() {
        return this.getName();
    }

    @Override
    public void action(GameLogic logic) {
        if (logic == null) {
            logic = GameLogic.getInstance();
        }
            logic.setLevelEnd();
    }
}