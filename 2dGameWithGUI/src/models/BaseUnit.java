package models;

import models.contracts.Unit;

public abstract class BaseUnit implements Unit {
    private int x;
    private int y;
    private boolean isAlive;
    protected BaseUnit(int x, int y){
        this.x = x;
        this.y = y;
        this.isAlive = true;
    }

    @Override
    public int getXPosition() {
        return x;
    }

    @Override
    public int getYPosition() {
        return y;
    }

    @Override
    public void moveTo(int row, int col) {
        this.x = col;
        this.y = row;
    }

    @Override
    public boolean isAlive(){
        return isAlive;
    }

    @Override
    public void setIsAlive(boolean value){
        this.isAlive = value;
    }
}
