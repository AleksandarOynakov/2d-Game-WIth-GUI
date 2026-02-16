package models;

public class Enemy extends BaseCharacter {
    private boolean isAlive;
    public Enemy(int x, int y){
        super(x,y);
        this.isAlive = true;
    }

    public boolean isAlive(){
        return isAlive;
    }

    public void setIsAlive(boolean value){
        this.isAlive = value;
    }
}
