package game.collidable.logic;

import game.auxiliary.Counter;
import game.collidable.object.Block;
import game.geometry.Ball;

/**
 * <h2>ScoreTrackingListener class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor method.
     * @param scoreCounter a Counter of the current Score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        currentScore = scoreCounter;
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
        currentScore.increase(5);
    }
}
