package com.codecool.dungeoncrawl.data.artifacts;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.logic.GameLogic;

public class Shield extends Item {


    public Shield(Cell cell) {
        super(cell, "shield");
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
        logic.addItemToInventory();
        logic.increaseDefense(3);
    }
}
