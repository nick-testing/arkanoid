package game.levels;

import game.LevelInformation;
import game.collidable.object.Block;

import game.geometry.Point;
import game.geometry.Rectangle;
import game.geometry.properties.Velocity;
import game.sprite.Circle;
import game.sprite.Oval;
import game.sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Level1 Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class Level1 implements LevelInformation {

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
        velocities.add(new Velocity(0, 5));
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
        return 150;
    }

    /**
     * @return the level name.
     */
    @Override
    public String levelName() {
        return "Darts Lounge";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a Sprite
     */
    @Override
    public List<Sprite> getBackground() {
        List<Sprite> spriteList = new ArrayList<Sprite>();
        spriteList.add(new Block(new Rectangle(new Point(0, 0), 800, 600), new Color(172, 157, 49)));
        spriteList.add(new Block(new Rectangle(new Point(100, 186), 30, 140), new Color(72, 72, 72)));
        spriteList.add(new Oval(65, 176, 100, 20, new Color(124, 78, 78)));
        spriteList.add(new Block(new Rectangle(new Point(90, 356), 30, 140), new Color(72, 72, 72)));
        spriteList.add(new Oval(55, 346, 100, 20, new Color(124, 78, 78)));
        spriteList.add(new Block(new Rectangle(new Point(30, 30), 20, 770), Color.ORANGE));
        spriteList.add(new Circle(400, 150, 100, Color.BLACK));
        spriteList.add(new Circle(400, 150, 80, Color.WHITE));
        spriteList.add(new Circle(400, 150, 60, Color.BLACK));
        spriteList.add(new Circle(400, 150, 40, Color.WHITE));
        spriteList.add(new Circle(400, 150, 20, Color.BLACK));
        spriteList.add(new Circle(400, 150, 20, Color.RED));
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
        blocks.add(new Block(new Rectangle(new Point(380, 140), 40, 20), Color.RED));
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
