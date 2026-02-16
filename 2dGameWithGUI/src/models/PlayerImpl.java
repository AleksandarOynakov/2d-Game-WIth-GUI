package models;

import models.contracts.Character;

public class PlayerImpl implements Character {
    private int x;
    private int y;

    public PlayerImpl(int x, int y){
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
}
