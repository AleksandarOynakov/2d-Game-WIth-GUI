package models;


public class Door extends BaseObjective {
    private boolean isOpen;

    public Door(int rows, int cols) {
        super(rows, cols);
        this.isOpen = false;
    }

    public boolean isOpen(){
        return this.isOpen;
    }

    public void setOpen(){
        this.isOpen = true;
    }

    public void setClosed(){
        this.isOpen = false;
    }
}
