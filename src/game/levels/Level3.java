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
 * <h2>Level3 Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class Level3 implements LevelInformation {

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
        velocities.add(new Velocity(0, 15));
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
        return 120;
    }

    /**
     * @return the level name.
     */
    @Override
    public String levelName() {
        return "Tel Aviv - Eilat";
    }

    /**
     * Returns a sprite List with the background of the level.
     *
     * @return a Sprite
     */
    @Override
    public List<Sprite> getBackground() {
        List<Sprite> spriteList = new ArrayList<Sprite>();
        //background
        spriteList.add(new Block(new Rectangle(new Point(0, 0), 800, 600), new Color(0, 162, 232)));
        //front
        spriteList.add(new Circle(400, 450, 50, new Color(180, 180, 180)));
        spriteList.add(new Circle(400, 450, 15, Color.GRAY));
        //windows
        spriteList.add(new Block(new Rectangle(new Point(405, 412), 20, 15), new Color(0, 162, 232)));
        spriteList.add(new Block(new Rectangle(new Point(375, 412), 20, 15), new Color(0, 162, 232)));
        //wings
        spriteList.add(new Oval(210, 440, 150, 25, new Color(180, 180, 180)));
        spriteList.add(new Oval(440, 440, 150, 25, new Color(180, 180, 180)));
        //engine Circles
        spriteList.add(new Circle(280, 470, 20, new Color(180, 180, 180)));
        spriteList.add(new Circle(520, 470, 20, new Color(180, 180, 180)));
        //tail wing
        spriteList.add(new Oval(392, 330, 15, 80, new Color(180, 180, 180)));
        spriteList.add(new Oval(352, 360, 90, 7, new Color(180, 180, 180)));

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
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 10; i++) {
                Rectangle rect = new Rectangle(new Point(696 - 74 * i, 120 + 20 * j), 74, 20);
                Block block;
                if (j == 0) {
                    block = new Block(rect, Color.GRAY);
                } else if (j == 1) {
                    block = new Block(rect, Color.RED);
                } else if (j == 2) {
                    block = new Block(rect, Color.YELLOW);
                } else if (j == 3) {
                    block = new Block(rect, Color.ORANGE);
                } else if (j == 4) {
                    block = new Block(rect, Color.PINK);
                } else {
                    block = new Block(rect, Color.GREEN);
                }
                blocks.add(block);
            }
        }        return blocks;
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
