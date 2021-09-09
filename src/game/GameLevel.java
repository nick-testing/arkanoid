package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.auxiliary.Counter;
import game.collidable.Collidable;
import game.collidable.logic.BallRemover;
import game.collidable.logic.BlockRemover;
import game.collidable.logic.HitListener;
import game.collidable.logic.ScoreTrackingListener;
import game.features.KeyPressStoppableAnimation;
import game.features.PauseScreen;
import game.geometry.properties.Velocity;
import game.sprite.LevelName;
import game.sprite.ScoreIndicator;
import game.sprite.Sprite;
import game.sprite.SpriteCollection;
import game.geometry.Rectangle;
import game.geometry.Ball;
import game.geometry.Point;
import game.collidable.object.Block;
import game.collidable.object.Paddle;
import game.animation.Animation;
import game.animation.AnimationRunner;
import java.awt.Color;
import java.util.List;

/**
 * <h2>Game Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private Counter remainingBlocks = new Counter(0);
    private Counter remainingBalls = new Counter(0);
    private Counter score;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private boolean running;
    private final LevelInformation info;
    //static variables
    public static final int GUI_DIMENSION_X =  800;
    public static final int GUI_DIMENSION_Y =  600;
    public static final int BORDER_WIDTH = 30;

    /**
     * Constructor method.
     * @param levelInfo LevelInformation
     * @param ar AnimationRunner
     * @param ks KeyboardSensor
     * @param scoreCounter Counter
     */
    public GameLevel(LevelInformation levelInfo, AnimationRunner ar, KeyboardSensor ks, Counter scoreCounter) {
        this.info = levelInfo;
        this.runner = ar;
        this.keyboard = ks;
        this.score = scoreCounter;
    }

    /**
     * adds Collidable object to the Game object.
     * @param c Collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * adds Sprite object to the Game object.
     * @param s Sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initializes the game object, creates blocks, Ball and a paddle.
     */
    public void initialize() {
        for (Sprite i : info.getBackground()) {
            sprites.addSprite(i);
        }
        //adds all the Balls to the Game level
        addBallsOnTopOfPaddle(info.numberOfBalls(), info.initialBallVelocities());

        //Listeners
        HitListener blockRemover = new BlockRemover(this, remainingBlocks);
        HitListener ballRemover = new BallRemover(this, remainingBalls);
        HitListener scoreTracker = new ScoreTrackingListener(score);

        //killing block
        Rectangle c = new Rectangle(new Point(30, 600), 740, 30);
        Block killer = new Block(c, Color.GRAY);
        killer.addHitListener(ballRemover);
        killer.addToGame(this);

        //border Blocks
        Rectangle a = new Rectangle(new Point(0, 0), 800, 30);
        Rectangle b = new Rectangle(new Point(0, 30), BORDER_WIDTH, 570);
        Rectangle d = new Rectangle(new Point(770, 30), BORDER_WIDTH, 570);
        new Block(a, Color.GRAY).addToGame(this);
        new Block(b, Color.GRAY).addToGame(this);
        new Block(d, Color.GRAY).addToGame(this);

        //and the Paddle from the LevelInfo to the game
        createAPaddle(info.paddleWidth(), info.paddleSpeed());

        //adds the Blocks from the LevelInfo variable to the game
        addBlocks(info.blocks(), blockRemover, scoreTracker);

        //adds ScoreIndicator to the game.
        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);

        //adds a name indicator to the game.
        LevelName lvlName = new LevelName(info.levelName());
        lvlName.addToGame(this);
        remainingBlocks.increase(info.numberOfBlocksToRemove()); //tracks how many balls should be removed to win


    }

    /**
     * Run the game.
     */
    public void run() {
        this.running = true;
        this.runner.run(this);
    }

    /**
     * adds Blocks to the game, applies a ScoreTracker and BlockRemover to each Block.
     * @param blockList List of Blocks.
     * @param blockRemover HitListener
     * @param scoreTracker HitListener
     */
    private void addBlocks(List<Block> blockList, HitListener blockRemover, HitListener scoreTracker) {
        for (Block i : blockList) {
            i.addHitListener(blockRemover);
            i.addHitListener(scoreTracker);
            i.addToGame(this);
        }
    }

    /**
     * adds a Paddle to the Game.
     * @param speed integer value
     * @param width integer value
     */
    private void createAPaddle(int width, int speed) {
        Paddle paddle = new Paddle(this.keyboard,
                new Rectangle(new Point(Math.floor(400 - width / 2), 550), width, 20), speed);
        paddle.addToGame(this);
    }

    /**
     * removes a collidableObject from the gameEnvironment field.
     * @param c Collidable object
     */
    public void removeCollidable(Collidable c) {
        this.environment.remove(c);
    }

    /**
     * removes a Sprite object from the  field.
     * @param s Sprite object
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * returns the GameEnvironment field.
     * @return environment
     */
    public GameEnvironment getEnv() {
        return this.environment;
    }

    /**
     * This method adds all the game blocks that can be removed to the game.
     * @param blockRemover HitListener for Block removal
     * @param scoreTracker HitListener for score tracking
     */
    private void addLevelBlocks(HitListener blockRemover, HitListener scoreTracker) {
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
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTracker);
                block.addToGame(this);
            }
        }
    }

    /**
     * Calls every Object registered as part of the animation to make single frame actions.
     *
     * @param d a DrawSurface object
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        if (remainingBlocks.getValue() <= 0 || remainingBalls.getValue() <= 0) {
            if (remainingBlocks.getValue() <= 0) {
                score.increase(100);
            }
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        this.sprites.notifyAllTimePassed();
    }

    /**
     * Indicates if the animation should stop or not.
     *
     * @return true if should, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * adds a certain amount of Balls above the Paddle.
     * @param initialVelocity List with Velocity parameter
     * @param numOfBalls number of Balls
     */
    private void addBallsOnTopOfPaddle(int numOfBalls, List<Velocity> initialVelocity) {
        for (int i = 0; i < numOfBalls; ++i) {
            Ball ball = new Ball(400 + ((-1) ^ i) * (30 * i) , 360 - 20 * i, 5, Color.WHITE);
            if (initialVelocity.size() > numOfBalls) {
                ball.setVelocity(10, 0); //default behaviour
            } else {
                ball.setVelocity(initialVelocity.get(i));
            }
            ball.addToGame(this);
            this.remainingBalls.increase(1);
        }
    }

    /**
     * returns how many balls are left.
     * @return ammount of balls
     */
    public int ballsLeft() {
        return remainingBalls.getValue();
    }
}
