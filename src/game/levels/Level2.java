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
 * <h2>Level2 Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class Level2 implements LevelInformation {

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
        velocities.add(new Velocity(0, 7));
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
        return 275;
    }

    /**
     * @return the level name.
     */
    @Override
    public String levelName() {
        return "Classic Arkanoid";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a Sprite
     */
    @Override
    public List<Sprite> getBackground() {
        List<Sprite> spriteList = new ArrayList<Sprite>();
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
        for (int j = 12; j >= 7; j--) {
            for (int i = 0; i < j; i++) {
                Rectangle rect = new Rectangle(new Point(720 - 50 * i, 120 + 20 * (12 - j)), 50, 20);
                Block block;
                if (j == 12) {
                    block = new Block(rect, Color.GRAY);
                } else if (j == 11) {
                    block = new Block(rect, Color.RED);
                } else if (j == 10) {
                    block = new Block(rect, Color.YELLOW);
                } else if (j == 9) {
                    block = new Block(rect, Color.CYAN);
                } else if (j == 8) {
                    block = new Block(rect, Color.PINK);
                } else {
                    block = new Block(rect, Color.GREEN);
                }
                blocks.add(block);
            }
        }
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
