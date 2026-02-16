package core;

import drawers.GameFrame;
import drawers.GamePanel;
import models.Cell;
import models.PlayerImpl;
import models.contracts.Character;
import models.enums.CellType;
import utils.ImageLoader;

import java.awt.image.BufferedImage;

public class Board {
    private final int rows = 11;
    private final int cols = 11;
    private final BufferedImage wall;
    private final BufferedImage grass;
    private GamePanel panel;
    private Character player;
    private final Cell[][] grid;

    public Board(){
        wall = ImageLoader.load("/textures/Wall.png");
        grass = ImageLoader.load("/textures/Grass.png");
        grid = new Cell[rows][cols];
        initGrid();
        this.player = new PlayerImpl(5,5);
        panel = new GamePanel(grid,player);
        new GameFrame(panel);
    }

    private void initGrid() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (r == 0 || r == rows - 1 || c == 0 || c == cols - 1) {
                    grid[r][c] = new Cell(CellType.WALL, false, wall);
                } else {
                    grid[r][c] = new Cell(CellType.GRASS, true, grass);
                }
            }
        }
    }
}

