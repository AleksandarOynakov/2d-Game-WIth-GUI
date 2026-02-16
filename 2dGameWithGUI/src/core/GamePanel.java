package core;

import models.enums.CellType;
import models.Cell;
import utils.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    private final int rows = 10;
    private final int cols = 10;
    private final int cellSize = 50;
    private final BufferedImage wall;
    private final BufferedImage grass;
    private final Cell[][] grid;


    public GamePanel() {
        setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
        setBackground(Color.gray);
        grid = new Cell[rows][cols];
        wall = ImageLoader.load("/textures/Wall.png");
        grass = ImageLoader.load("/textures/Grass.png");
        initGrid();
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * cellSize;
                int y = row * cellSize;

                Cell cell = grid[row][col];

                g.drawImage(cell.getSprite(), x, y, cellSize, cellSize, null);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellSize, cellSize);
            }
        }
    }
}
