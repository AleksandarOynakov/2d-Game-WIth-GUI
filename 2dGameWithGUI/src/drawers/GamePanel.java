package drawers;

import core.Board;
import models.Cell;
import models.contracts.Character;
import utils.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    private final int cellSize = 100;
    private final Board board;
    private final Character player;
    private final BufferedImage knightSprite;

    public GamePanel(Board board, Character player) {
        this.board = board;
        this.player = player;
        knightSprite = ImageLoader.load("/textures/Knight.png");
        setPreferredSize(new Dimension(board.getCols() * cellSize, board.getRows() * cellSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < board.getGrid().length; row++) {
            for (int col = 0; col < board.getGrid()[row].length; col++) {
                int x = col * cellSize;
                int y = row * cellSize;

                Cell cell = board.getGrid()[row][col];

                g.drawImage(cell.getSprite(), x, y, cellSize, cellSize, null);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellSize, cellSize);
            }
        }
        int playerX = player.getXPosition() * cellSize;
        int playerY = player.getYPosition() * cellSize;
        g.drawImage(knightSprite,playerX,playerY,cellSize,cellSize,null);
    }
}
