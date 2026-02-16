package core;

import models.Enemy;
import models.contracts.Character;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameEngine {

    private final Board board;
    private final Character character;
    private final List<Enemy> listOfEnemies;

    public GameEngine(Board board, Character character) {
        this.board = board;
        this.character = character;
        listOfEnemies = new ArrayList<>();
    }

    public Board getBoard() {
        return board;
    }

    public Character getPlayer() {
        return character;
    }

    public List<Enemy> getListOfEnemies(){
        return this.listOfEnemies;
    }

    public void movePlayer(int rowDirection, int colDirection) {
        spawnEnemy();
        moveEnemies();

        int newRow = character.getYPosition() + rowDirection;
        int newCol = character.getXPosition() + colDirection;

        if (board.isWalkable(newRow, newCol)) {
            character.moveTo(newRow, newCol);
            if(listOfEnemies.stream().anyMatch(enemy -> enemy.getYPosition() == newRow && enemy.getXPosition() == newCol)){
                character.setIsAlive(false);
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
