package core;

import models.contracts.Player;

public class GameEngine {

    private final Board board;
    private final Player player;

    public GameEngine(Board board, Player player) {
        this.board = board;
        this.player = player;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer() {
        return player;
    }

    public void movePlayer(int rowDirection, int colDirection) {
        int newRow = player.getYPosition() + rowDirection;
        int newCol = player.getXPosition() + colDirection;

        if (board.isWalkable(newRow, newCol)) {
            player.moveTo(newRow, newCol);
        }
    }
}
