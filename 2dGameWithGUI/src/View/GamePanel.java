package View;

import core.Board;
import core.GameEngine;
import models.Cell;
import models.contracts.Player;
import utils.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    private final int cellSize = 100;
    private final GameEngine engine;
    private final BufferedImage wallSprite;
    private final BufferedImage grassSprite;
    private final BufferedImage portalSprite;
    private final BufferedImage knightSprite;

    public GamePanel(GameEngine engine) {
       this.engine = engine;

        wallSprite = ImageLoader.load("/textures/Wall.png");
        grassSprite = ImageLoader.load("/textures/Grass.png");
        portalSprite = ImageLoader.load("/textures/Portal.png");
        knightSprite = ImageLoader.load("/textures/Knight.png");
        setPreferredSize(new Dimension(engine.getBoard().getCols() * cellSize, engine.getBoard().getRows() * cellSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < engine.getBoard().getRows(); row++) {
            for (int col = 0; col < engine.getBoard().getCols(); col++) {
                int x = col * cellSize;
                int y = row * cellSize;

                Cell cell = engine.getBoard().getCell(row,col);
                setCellSprite(cell);

                g.drawImage(cell.getSprite(), x, y, cellSize, cellSize, null);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellSize, cellSize);
            }
        }
        int playerX = engine.getPlayer().getXPosition() * cellSize;
        int playerY = engine.getPlayer().getYPosition() * cellSize;
        g.drawImage(knightSprite,playerX,playerY,cellSize,cellSize,null);
    }

    private void setCellSprite(Cell cell){
        switch (cell.getType()){
            case WALL -> cell.setSprite(wallSprite);
            case GRASS -> cell.setSprite(grassSprite);
            case PORTAL -> cell.setSprite(portalSprite);
        }
    }
}
