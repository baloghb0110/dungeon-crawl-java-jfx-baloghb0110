package com.codecool.dungeoncrawl.data.customitems;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.artifacts.Item;
import com.codecool.dungeoncrawl.logic.GameLogic;

public class Life extends Item {

    public Life(Cell cell) {
        super(cell, "life");
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
        logic.increaseHealth(4);
        logic.removeItemFromMap();
    }
}
