package view;

import core.GameEngine;
import models.Cell;
import models.Door;
import models.Key;
import models.contracts.Locatable;
import models.contracts.Player;
import utils.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Function;
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
    private BufferedImage doorClosedSprite;
    private BufferedImage doorOpenSprite;
    private BufferedImage keySprite;
    private JButton restartButton;
    private JButton continueButton;
    private Timer gameUpdateTimer;
    private int currentDelay = 500;

    public GamePanel(GameEngine engine) {
        this.engine = engine;

        loadSprites();

        setPreferredSize(new Dimension(engine.getBoard().getCols() * cellSize, engine.getBoard().getRows() * cellSize));
        setFocusable(true);
        requestFocusInWindow();

        addRestartButton();
        addContinueButton();

        initTimer();
    }

    private void loadSprites() {
        wallSprite = ImageLoader.load("/textures/Wall.png");
        grassSprite = ImageLoader.load("/textures/Grass.png");
        portalSprite = ImageLoader.load("/textures/Portal.png");
        fireSprite = ImageLoader.load("/textures/Fire.png");
        fireSplashSprite = ImageLoader.load("/textures/FireSplash.png");
        knightDeathSprite = ImageLoader.load("/textures/KnightDeath.png");
        knightSprite = ImageLoader.load("/textures/Knight.png");
        doorClosedSprite = ImageLoader.load("/textures/DoorClosed.png");
        doorOpenSprite = ImageLoader.load("/textures/DoorOpen.png");
        keySprite = ImageLoader.load("/textures/Key.png");
    }

    private void initTimer() {
        gameUpdateTimer = new Timer(currentDelay, e -> {
            if (!engine.isGameOver()) {
                engine.update();
                repaint();
            }
        });
        gameUpdateTimer.start();
    }

    private void restartGame() {
        engine.reset();
        restartButton.setVisible(false);
        continueButton.setVisible(false);
        requestFocusInWindow();
        gameUpdateTimer.start();
        repaint();
    }

    private void addRestartButton() {
        restartButton = new JButton("Restart");
        restartButton.setVisible(false);
        restartButton.addActionListener(e -> restartGame());
        restartButton.setHorizontalAlignment(SwingConstants.CENTER);
        add(restartButton);
    }

    private void addContinueButton(){
        continueButton = new JButton("Continue");
        continueButton.setVisible(false);
        continueButton.addActionListener(e -> {
            currentDelay = Math.max(100, currentDelay - 50);
            gameUpdateTimer.setDelay(currentDelay);
            restartGame();
        });
        continueButton.setHorizontalAlignment(SwingConstants.CENTER);
        add(continueButton);
    }

    private void setCellSprite(Cell cell) {
        switch (cell.getType()) {
            case WALL -> cell.setSprite(wallSprite);
            case GRASS -> cell.setSprite(grassSprite);
            case PORTAL -> cell.setSprite(portalSprite);
        }
    }

    private void drawPlayground(Graphics g) {
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
    }

    private void drawEnemies(Graphics g) {
        engine.getListOfEnemies().forEach(enemy -> {
            int enemyX = enemy.getXPosition() * cellSize;
            int enemyY = enemy.getYPosition() * cellSize;
            BufferedImage enemySprite = enemy.isAlive() ? fireSprite : fireSplashSprite;
            g.drawImage(enemySprite, enemyX, enemyY, cellSize, cellSize, null);
        });
    }

    private <E extends Locatable> void drawObject(Graphics g, E obj, Function<E, Boolean> checkState, BufferedImage spriteTrue, BufferedImage spriteFalse) {
        int positionX = obj.getXPosition() * cellSize;
        int positionY = obj.getYPosition() * cellSize;
        BufferedImage sprite = checkState.apply(obj) ? spriteTrue : spriteFalse;
        g.drawImage(sprite, positionX, positionY, cellSize, cellSize, null);
    }

    private int calculateGameOverTextX(int textWidth) {
        return (getWidth() - textWidth) / 2;
    }

    private int calculateGameOverTextY(FontMetrics fm) {
        return (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
    }


    private void drawText(Graphics2D g2, String text, int textX, int textY) {
        g2.setColor(new Color(0, 0, 0, 170));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.RED);
        g2.drawString(text, textX, textY);
    }

    private <E extends JButton> void makeButtonVisible(int textY, E button){
        int buttonWidth = 160;
        int buttonHeight = 40;
        int buttonX = (getWidth() - buttonWidth) / 2;
        int buttonY = textY + 40;

        button.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        button.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawPlayground(g);
        drawObject(g, engine.getDoor(), Door::isOpen, doorOpenSprite, doorClosedSprite);
        drawObject(g, engine.getKey(), Key::isCollected, null, keySprite);
        drawEnemies(g);
        drawObject(g, engine.getPlayer(), Player::isAlive, knightSprite,knightDeathSprite);


        if (engine.isGameOver() || engine.isLevelCompleted()) {
            gameUpdateTimer.stop();

            Graphics2D g2 = (Graphics2D) g;
            String text = engine.isGameOver()? "GAME OVER" : "LEVEL COMPLETED!";
            g2.setFont(new Font("Arial", Font.BOLD, 48));
            FontMetrics fm = g2.getFontMetrics();

            int textWidth = fm.stringWidth(text);
            int textX = calculateGameOverTextX(textWidth);
            int textY = calculateGameOverTextY(fm);

            drawText(g2, text, textX, textY);

            if (engine.isGameOver()) {
                makeButtonVisible(textY,restartButton);
            } else {
                makeButtonVisible(textY,continueButton);
            }
        }
    }
}
