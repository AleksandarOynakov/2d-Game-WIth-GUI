package core.input;

import core.GameEngine;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {

    private final GameEngine engine;
    private final JComponent componentToRepaint;

    public InputHandler(GameEngine engine, JComponent componentToRepaint) {
        this.engine = engine;
        this.componentToRepaint = componentToRepaint;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!engine.isGameOver()){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W, KeyEvent.VK_UP -> engine.movePlayer(-1, 0);
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> engine.movePlayer(1, 0);
                case KeyEvent.VK_A, KeyEvent.VK_LEFT -> engine.movePlayer(0, -1);
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> engine.movePlayer(0, 1);
            }
            componentToRepaint.repaint();
        }
    }
}