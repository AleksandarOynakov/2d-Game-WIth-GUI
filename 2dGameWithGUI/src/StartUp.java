import core.Board;
import drawers.GameFrame;
import drawers.GamePanel;

public class StartUp {
    public static void main(String[] args) {
        Board board = new Board();
        GamePanel panel = new GamePanel(board);
        GameFrame frame = new GameFrame(panel);
    }
}