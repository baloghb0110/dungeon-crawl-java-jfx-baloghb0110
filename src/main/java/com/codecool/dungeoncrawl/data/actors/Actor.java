package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.logic.GameLogic;

import java.util.Random;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;

    private static final int NUM_DIRECTIONS = 4;
    private static final int[] DX = {0, 0, -1, 1};
    private static final int[] DY = {-1, 1, 0, 0};
    private Random random;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
        random = new Random();
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;

    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void makeRandomMove(){
        int randomDirectionX = random.nextInt(NUM_DIRECTIONS);
        int randomDirectionY = random.nextInt(NUM_DIRECTIONS);

        int dx = DX[randomDirectionX];
        int dy = DY[randomDirectionY];

        if(isValidMove(dx, dy)){
            move(dx, dy);
        }
    }

    public boolean isValidMove(int x, int y){
        Cell nextCell = getCell().getNeighbor(x, y);
        return nextCell != null && nextCell.getType() == CellType.FLOOR && nextCell.getActor() == null;
    }
}
