package game.collidable.logic;

import game.GameLevel;
import game.auxiliary.Counter;
import game.collidable.object.Block;
import game.geometry.Ball;


/**
 * <h2>BlockRemover class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor method.
     * @param gameLevel Game object
     * @param remainingBlocks represents how many blocks are left
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Called whenever the beingHit object is hit.
     * This hitter parameter is the ball that collides with it.
     *
     * @param beingHit the Block that was hit
     * @param hitter   the Ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
        remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
    }
}
