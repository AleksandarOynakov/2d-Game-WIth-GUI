package models.contracts;

public interface Character {
    int getXPosition();
    int getYPosition();

    void moveTo(int row, int col);
}
