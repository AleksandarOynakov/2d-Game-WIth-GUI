package models;

public class Key extends BaseObjective{
    private boolean isCollected;
    public Key(int rows, int cols) {
        super(rows, cols);
        this.isCollected = false;
    }

    public boolean isCollected(){
        return this.isCollected;
    }

    public void setCollected(){
        this.isCollected = true;
    }

    public void setNotCollected(){
        this.isCollected = false;
    }
}
