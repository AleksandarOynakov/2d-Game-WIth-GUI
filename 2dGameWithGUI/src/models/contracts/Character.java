package models.contracts;

public interface Character extends Locatable {
    void moveTo(int row, int col);
    boolean isAlive();
    void setIsAlive(boolean value);
}
