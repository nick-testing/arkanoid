package game;

import game.collidable.object.Block;
import game.geometry.properties.Velocity;
import game.sprite.Sprite;

import java.util.List;

/**
 * <h2>LevelInformation interface.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public interface LevelInformation {
    /**
     *
     * @return number of Balls on current level
     */
    int numberOfBalls();

    /**
     * return a List of the initial velocities of each ball.
     * @return a Velocity List
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed of the Paddle
     */
    int paddleSpeed();

    /**
     *
     * @return the width of the Paddle
     */
    int paddleWidth();

    /**
     *
     * @return the level name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return a Sprite
     */
    List<Sprite> getBackground();

    /**
     * returns the Blocks that make up this level, each block contains its size, color and location.
     * @return a Block List
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed in order for a level to be considered cleared.
     * @return an integer
     */
    int numberOfBlocksToRemove();
}
