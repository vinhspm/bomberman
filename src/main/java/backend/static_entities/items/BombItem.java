package main.java.backend.static_entities.items;

import javafx.scene.image.Image;
import main.java.backend.Entity;
import main.java.backend.GameState;
import main.java.backend.agents.BomberMan;
import main.java.backend.agents.PlayerAgent;
import main.java.backend.static_entities.Brick;
import main.java.backend.static_entities.flames.Flame;
import main.java.utils.EntityType;
import main.java.utils.GameSound;
import main.java.utils.GridPosition;

public class BombItem extends Item {
    public BombItem(GridPosition position) {
        this.visible = false;
        this.blocked = false;
        this.destroyable = true;
        this.entityType = EntityType.bomb_item;
        this.position = position;

    }
    public void updateGameState(GameState gameState) {
        boolean checkBrick = false;
        boolean checkFlame = false;
        for (Entity e : gameState.getEntityList()) {
            if(e instanceof Brick && e.getPosition().distance(this.getPosition()) < 0.1) {
                checkBrick = true;
                return;
            }
        }
        if(!checkBrick) {
            for (Entity e : gameState.getEntityList()) {
                if(e instanceof Flame && e.getPosition().distance(this.getPosition()) <= 1) {
                    checkFlame = true;
                    return;
                }
            }
            if(!checkFlame) {
                this.visible = true;
            }
        }
        for(Entity e : gameState.getEntityList()) {
            if(e instanceof BomberMan && e.getPosition().distance(this.getPosition()) == 0) {
                int n = ((BomberMan) e).getRemainingBombs();
                ((BomberMan) e).setRemainingBombs(n+EXTRA_BOMB);
                gameState.removeEntity(this);
                GameSound item = new GameSound();
                item.playHitItemFx();

            }
        }
        decreaseTimeUntilVanish((double)1.0 / gameState.NUM_REFRESH_PER_TIME_UNIT);

        if (isVanished()) {
            gameState.removeEntity(this);

        }
    }

    @Override
    public Entity getClone() {
        return new BombItem(position);
    }

}
