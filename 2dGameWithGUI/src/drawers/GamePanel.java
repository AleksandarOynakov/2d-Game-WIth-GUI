package drawers;

import core.Board;
import models.Cell;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final int cellSize = 50;
    private final Board board;
    public GamePanel(Board board) {
        this.board = board;
        setPreferredSize(new Dimension(board.getCols() * cellSize, board.getRows() * cellSize));
        setBackground(Color.gray);
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
    }
}
