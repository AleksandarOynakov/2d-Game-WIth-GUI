import core.Board;
import drawers.GameFrame;
import drawers.GamePanel;
import models.PlayerImpl;
import models.contracts.Character;

public class StartUp {
    public static void main(String[] args) {
        Board board = new Board();
        Character player = new PlayerImpl(5,5);
        GamePanel panel = new GamePanel(board,player);
        new GameFrame(panel);
    }
}