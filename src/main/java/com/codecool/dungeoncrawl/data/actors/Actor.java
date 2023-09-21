package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Drawable;

import java.util.Random;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
  
    private static final int[] DX = {0, -1, 1};
    private static final int[] DY = {-1, 1, 0};
 
    private int damage = 3;
    private int defense = 1;

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


    private void removeDeadActor(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getActor() != null && nextCell.getActor().getHealth() <= 0) {
            nextCell.setActor(null);
        } else if (cell.getActor().getHealth() <= 0) {
            cell.setActor(null);
        }
    }

    private void checkIfAlive() {
        if (cell.getActor().getHealth() <= 0) {
            cell.setActor(null);
        }
    }

    public void attackNeighbouringActor(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor currentActor = cell.getActor();

        Actor neighbourActor = nextCell.getActor();

        if (nextCell.getActor() != null && currentActor instanceof Player) {
            neighbourActor.setHealth(neighbourActor.getHealth() - currentActor.getDamage());

            checkIfAlive();
            removeDeadActor(dx, dy);

            if (currentActor.getDefense() < neighbourActor.getDamage()) {
                currentActor.setHealth(currentActor.getHealth() -
                        (neighbourActor.getDamage() - currentActor.getDefense()));
                checkIfAlive();
                removeDeadActor(dx, dy);
            }
        }
    }

    public void setHealth(int health) {
        this.health = health;
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void makeRandomMove(){
        int randomDirectionX = random.nextInt(DX.length);
        int randomDirectionY = random.nextInt(DY.length);

        int dx = DX[randomDirectionX];
        int dy = DY[randomDirectionY];

        if(isValidMove(dx, dy) && cell.getActor() != null){
            move(dx, dy);
        }
    }

    public boolean isValidMove(int x, int y){
        Cell nextCell = getCell().getNeighbor(x, y);
        return nextCell != null &&
                nextCell.getType() == CellType.FLOOR &&
                nextCell.getActor() == null;
    }
}
