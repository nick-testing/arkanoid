package game.levels;

import game.LevelInformation;
import game.collidable.object.Block;

import game.geometry.Point;
import game.geometry.Rectangle;
import game.geometry.properties.Velocity;
import game.sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Level4 Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class Level4 implements LevelInformation {

    /**
     * @return number of Balls on current level
     */
    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    /**
     * return a List of the initial velocities of each ball.
     *
     * @return a Velocity List
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(new Velocity(0, 7));
        return velocities;
    }

    /**
     * @return the speed of the Paddle
     */
    @Override
    public int paddleSpeed() {
        return 15;
    }

    /**
     * @return the width of the Paddle
     */
    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     * @return the level name.
     */
    @Override
    public String levelName() {
        return "User vs. Big Tech";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a Sprite
     */
    @Override
    public List<Sprite> getBackground() {
        List<Sprite> spriteList = new ArrayList<Sprite>();
        //background
        spriteList.add(new Block(new Rectangle(new Point(0, 0), 800, 600), new Color(62, 69, 97)));
        return spriteList;
    }

    /**
     * returns the Blocks that make up this level, each block contains its size, color and location.
     *
     * @return a Block List
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        blocks.add(new Block(new Rectangle(new Point(293, 100), 100, 100), new Color(244, 98, 51)));
        blocks.add(new Block(new Rectangle(new Point(408, 100), 100, 100), new Color(152, 205, 25)));
        blocks.add(new Block(new Rectangle(new Point(293, 215), 100, 100), new Color(7, 169, 241)));
        blocks.add(new Block(new Rectangle(new Point(408, 215), 100, 100), new Color(254, 184, 1)));


        return blocks;
    }

    /**
     * Number of blocks that should be removed in order for a level to be considered cleared.
     *
     * @return an integer
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
