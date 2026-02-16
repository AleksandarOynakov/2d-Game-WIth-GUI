package core;

import models.Cell;
import models.enums.CellType;
import utils.ImageLoader;

import java.awt.image.BufferedImage;

public class Board {
    private final int rows = 11;
    private final int cols = 11;
    private final Cell[][] grid;

    public Board(){
        grid = new Cell[rows][cols];
        initGrid();
    }

    private void initGrid() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (r == 0 || r == rows - 1 || c == 0 || c == cols - 1) {
                    grid[r][c] = new Cell(CellType.WALL, false, null);
                } else {
                    grid[r][c] = new Cell(CellType.GRASS, true, null);
                }
            }
        }
    }

    public Cell[][] getGrid(){
        return grid;
    }

    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }

    public Cell getCell(int row, int col){
        return grid[row][col];
    }

    public boolean isWalkable(int row, int cols){
        return grid[row][cols].isWalkable();
    }
}

