package models;

import models.contracts.Character;

public abstract class BaseCharacter implements Character {
    private int x;
    private int y;

    public BaseCharacter(int x, int y){
        this.x = x;
        this.y = y;
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
}
