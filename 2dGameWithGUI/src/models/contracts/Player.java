package models.contracts;

public interface Player {
    int getXPosition();
    int getYPosition();

    void moveTo(int row, int col);
}
