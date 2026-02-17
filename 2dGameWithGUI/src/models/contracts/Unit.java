package models.contracts;

public interface Unit extends Locatable {
    void moveTo(int row, int col);
    boolean isAlive();
    void setIsAlive(boolean value);
}
