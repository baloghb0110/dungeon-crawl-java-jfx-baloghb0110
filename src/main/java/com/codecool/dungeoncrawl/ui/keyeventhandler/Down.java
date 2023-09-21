package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Down implements KeyHandler {
    public static final KeyCode code = KeyCode.DOWN;

    @Override
    public void perform(KeyEvent event, GameMap map) {
        if (code.equals(event.getCode()) && map.getPlayer().isValidMove(0, 1)) {
            map.getPlayer().move(0, 1);
        } else if (code.equals(event.getCode())) {
            map.getPlayer().attackNeighbouringActor(0, 1);
        }
    }
}
