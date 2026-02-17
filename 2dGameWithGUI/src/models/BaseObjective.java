package models;

import models.contracts.Objective;

import java.util.concurrent.ThreadLocalRandom;

public abstract class BaseObjective implements Objective {
    private int x;
    private int y;
    private int cols;
    private int rows;
    protected BaseObjective(int rows, int cols){
        this.cols = cols;
        this.rows = rows;
        generatePosition();
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
    public void generatePosition(){
        this.x = ThreadLocalRandom.current().nextInt(1,this.cols -1);
        this.y = ThreadLocalRandom.current().nextInt(1,this.rows -1);
    }
}
