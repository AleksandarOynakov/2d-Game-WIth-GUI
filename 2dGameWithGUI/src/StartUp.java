import core.Board;
import view.GameFrame;
import view.GamePanel;
import core.GameEngine;
import core.input.InputHandler;
import models.PlayerImpl;


public class StartUp {
    public static void main(String[] args) {
        Board board = new Board();
        PlayerImpl player = new PlayerImpl(5, 5);

        GameEngine engine = new GameEngine(board, player);

        GamePanel panel = new GamePanel(engine);
        panel.addKeyListener(new InputHandler(engine,panel));
        new GameFrame(panel);
        panel.requestFocusInWindow();
    }
}