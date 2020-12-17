package main.java.backend.agents;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.utils.Direction;
import main.java.utils.EntityType;
import main.java.utils.GridPosition;

import java.util.Random;

public class Oneal extends Agent {
    public Oneal(GridPosition position, double speed, int score) {
        super(position, speed, score);
        entityType = EntityType.oneal;
    }

    public Oneal getClone() {
        return new Oneal(position, speed, score);
    }

    public void updateGameState(GameState gameState) throws CloneNotSupportedException {
        if (isVanished()) {
            gameState.addScore(score);
            gameState.removeEntity(this);
            return;
        }

        decreaseTimeUntilVanish((double)1.0 / GameState.NUM_REFRESH_PER_TIME_UNIT);
        // standStill(gameState);
        // randomMove(gameState);
        chasePlayer(gameState);
        movingType = getStep();
    }
}
