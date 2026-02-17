package models;

import models.contracts.Player;

public class PlayerImpl extends BaseUnit implements Player {
    private final int startX;
    private final int startY;
    public PlayerImpl(int x, int y){
        super(x,y);
        this.startX = x;
        this.startY = y;
    }

    public void resetPosition(){
        this.moveTo(startX,startY);
    }
}
