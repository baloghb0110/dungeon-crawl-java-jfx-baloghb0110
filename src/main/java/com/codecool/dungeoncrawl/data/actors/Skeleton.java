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

    private boolean isPlayerInRange(Player player, Skeleton skeleton) {
        int dx = Math.abs(player.getX() - skeleton.getX());
        int dy = Math.abs(player.getY() - skeleton.getY());

        int chaseRange = 5;

        return dx <= chaseRange && dy <= chaseRange;
    }
}
