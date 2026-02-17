package view;

import core.GameEngine;
import models.Cell;
import utils.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

public class GamePanel extends JPanel {
    private final int cellSize = 100;
    private final GameEngine engine;
    private BufferedImage wallSprite;
    private BufferedImage grassSprite;
    private BufferedImage portalSprite;
    private BufferedImage fireSprite;
    private BufferedImage fireSplashSprite;
    private BufferedImage knightDeathSprite;
    private BufferedImage knightSprite;
    private final JButton restartButton;
    private Timer gameUpdateTimer;


    public GamePanel(GameEngine engine) {
        this.engine = engine;

        loadSprites();

        setPreferredSize(new Dimension(engine.getBoard().getCols() * cellSize, engine.getBoard().getRows() * cellSize));
        setFocusable(true);
        requestFocusInWindow();

        restartButton = new JButton("Restart");
        restartButton.setVisible(false);
        restartButton.addActionListener(e -> restartGame());
        restartButton.setHorizontalAlignment(SwingConstants.CENTER);
        add(restartButton);

        gameUpdateTimer = new Timer(1000, e -> {
            if (!engine.isGameOver()) {
                engine.update();
                repaint();
            }
        });

        gameUpdateTimer.start();
    }

    private void loadSprites() {
        wallSprite = ImageLoader.load("/textures/Wall.png");
        grassSprite = ImageLoader.load("/textures/Grass.png");
        portalSprite = ImageLoader.load("/textures/Portal.png");
        fireSprite = ImageLoader.load("/textures/Fire.png");
        fireSplashSprite = ImageLoader.load("/textures/FireSplash.png");
        knightDeathSprite = ImageLoader.load("/textures/KnightDeath.png");
        knightSprite = ImageLoader.load("/textures/Knight.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < engine.getBoard().getRows(); row++) {
            for (int col = 0; col < engine.getBoard().getCols(); col++) {
                int x = col * cellSize;
                int y = row * cellSize;

                Cell cell = engine.getBoard().getCell(row, col);
                setCellSprite(cell);

                g.drawImage(cell.getSprite(), x, y, cellSize, cellSize, null);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellSize, cellSize);
            }
        }

        engine.getListOfEnemies().forEach(enemy -> {
            int enemyX = enemy.getXPosition() * cellSize;
            int enemyY = enemy.getYPosition() * cellSize;
            BufferedImage enemySprite = enemy.isAlive() ? fireSprite : fireSplashSprite;
            g.drawImage(enemySprite, enemyX, enemyY, cellSize, cellSize, null);
        });

        int playerX = engine.getPlayer().getXPosition() * cellSize;
        int playerY = engine.getPlayer().getYPosition() * cellSize;
        BufferedImage playerSprite = engine.getPlayer().isAlive() ? knightSprite : knightDeathSprite;
        g.drawImage(playerSprite, playerX, playerY, cellSize, cellSize, null);

        if (engine.isGameOver()) {
            gameUpdateTimer.stop();
            Graphics2D g2 = (Graphics2D) g;

            g2.setColor(new Color(0, 0, 0, 170));
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.setColor(Color.RED);
            g2.setFont(new Font("Arial", Font.BOLD, 48));

            String text = "GAME OVER";
            FontMetrics fm = g2.getFontMetrics();

            int textWidth = fm.stringWidth(text);
            int textX = (getWidth() - textWidth) / 2;
            int textY = getHeight() / 2;

            g2.drawString(text, textX, textY);

            int buttonWidth = 160;
            int buttonHeight = 40;
            int buttonX = (getWidth() - buttonWidth) / 2;
            int buttonY = textY + 40;

            restartButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
            restartButton.setVisible(true);
        }
    }

    private void setCellSprite(Cell cell) {
        switch (cell.getType()) {
            case WALL -> cell.setSprite(wallSprite);
            case GRASS -> cell.setSprite(grassSprite);
            case PORTAL -> cell.setSprite(portalSprite);
        }
    }

    private void restartGame() {
        engine.reset();
        restartButton.setVisible(false);
        requestFocusInWindow();
        gameUpdateTimer.start();
        repaint();
    }

}
