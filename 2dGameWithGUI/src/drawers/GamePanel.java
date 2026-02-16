package drawers;

import core.Board;
import models.Cell;
import models.PlayerImpl;
import models.contracts.Character;
import utils.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    private final int cellSize = 100;
    private final Cell[][] grid;
    private final Character player;

    public GamePanel(Cell[][] grid, Character player) {
        this.grid = grid;
        this.player = player;
        setPreferredSize(new Dimension(grid.length * cellSize, grid.length * cellSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int x = col * cellSize;
                int y = row * cellSize;

                Cell cell = grid[row][col];

                g.drawImage(cell.getSprite(), x, y, cellSize, cellSize, null);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellSize, cellSize);
            }
        }
        int playerX = player.getXPosition() * cellSize;
        int playerY = player.getYPosition() * cellSize;
        g.drawImage(player.getSprite(),playerX,playerY,cellSize,cellSize,null);
    }
}
