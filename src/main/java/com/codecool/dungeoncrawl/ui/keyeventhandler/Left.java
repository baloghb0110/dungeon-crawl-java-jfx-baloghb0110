package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Left implements KeyHandler {
    public static final KeyCode code = KeyCode.LEFT;

    @Override
    public void perform(KeyEvent event, GameMap map) {
        if(code.equals(event.getCode()) && map.getPlayer().isValidMove(-1, 0)) {
            map.getPlayer().move(-1, 0);
        } else if (code.equals(event.getCode())) {
            map.getPlayer().attackNeighbouringActor(-1, 0);
        }
    }
}
