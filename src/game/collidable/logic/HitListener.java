package game.collidable.logic;

import game.collidable.object.Block;
import game.geometry.Ball;

/**
 * <h2>HitListener interface.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public interface HitListener {

    /**
     * Called whenever the beingHit object is hit.
     * This hitter parameter is the ball that collides with it.
     * @param beingHit the Block that was hit
     * @param hitter the Ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
