package game.collidable.logic;

import game.GameLevel;
import game.auxiliary.Counter;
import game.collidable.object.Block;
import game.geometry.Ball;

/**
 * <h2>BallRemover class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;
    /**
     * Constructor method.
     * @param gameLevel Game object
     * @param remainingBalls represents how many blocks are left
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
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
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }
}
