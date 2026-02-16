package models;

import models.contracts.Character;
import utils.ImageLoader;

import java.awt.image.BufferedImage;

public class PlayerImpl implements Character {
    private int x;
    private int y;
    private BufferedImage sprite;

    public PlayerImpl(int x, int y){
        this.x = x;
        this.y = y;
        this.sprite = ImageLoader.load("/textures/Knight.png");
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
    public BufferedImage getSprite(){
        return sprite;
    }
}
