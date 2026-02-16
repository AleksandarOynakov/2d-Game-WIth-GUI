package models;

import models.enums.CellType;

import java.awt.image.BufferedImage;

public class Cell {
    private CellType type;
    private boolean isWalkable;
    private BufferedImage sprite;

    public Cell(CellType type, boolean isWalkable, BufferedImage sprite){
        this.type = type;
        this.isWalkable = isWalkable;
        this.sprite = sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public boolean isWalkable(){
        return isWalkable;
    }

    public CellType getType() {
        return type;
    }


}
