package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    public void chasePlayer(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();
        int dx = Integer.compare(playerX, getX());
        int dy = Integer.compare(playerY, getY());

        if (isValidMove(dx, dy)) {
            move(dx, dy);
        }
    }
}
