package com.codecool.dungeoncrawl.data.artifacts;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.logic.GameLogic;

public class Key extends Item {

    public Key(Cell cell) {
        super(cell, "key");
    }

    private GameLogic logic;
    @Override
    public String getTileName() {
        return "key";
    }


    @Override
    public void action(GameLogic logic) {
        if (logic == null) {
            logic = GameLogic.getInstance();
        }
        logic.addItemToInventory();
    }
}
