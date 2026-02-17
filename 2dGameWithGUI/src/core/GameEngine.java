package core;

import models.Enemy;
import models.contracts.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameEngine {

    private final Board board;
    private final Player player;
    private final List<Enemy> listOfEnemies;
    private boolean gameOver;

    public GameEngine(Board board, Player player) {
        this.board = board;
        this.player = player;
        listOfEnemies = new ArrayList<>();
        this.gameOver = false;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getListOfEnemies(){
        return this.listOfEnemies;
    }

    public void setGameOver(boolean value){
        this.gameOver = value;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public void reset(){
        listOfEnemies.clear();
        player.setIsAlive(true);
        player.resetPosition();
        gameOver = false;
    }



    public void movePlayer(int rowDirection, int colDirection) {
        spawnEnemy();
        moveEnemies();

        int newRow = player.getYPosition() + rowDirection;
        int newCol = player.getXPosition() + colDirection;

        if (board.isWalkable(newRow, newCol)) {
            player.moveTo(newRow, newCol);
            if(listOfEnemies.stream().anyMatch(enemy -> enemy.getYPosition() == newRow && enemy.getXPosition() == newCol)){
                player.setIsAlive(false);
                gameOver = true;
            }
        }
    }

    private void spawnEnemy(){
        int randomX = ThreadLocalRandom.current().nextInt(1, board.getRows() - 1);
        Enemy fire = new Enemy(randomX,0);
        listOfEnemies.add(fire);
    }

    private void moveEnemies() {
        Iterator<Enemy> iterator = listOfEnemies.iterator();

        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();

            int newRow = enemy.getYPosition() + 1;
            int currentCol = enemy.getXPosition();

            if(newRow == board.getRows() -1){
                enemy.setIsAlive(false);
            }

            if (newRow >= board.getRows()) {
                iterator.remove();
            } else {
                enemy.moveTo(newRow, currentCol);
            }
        }
    }

}
