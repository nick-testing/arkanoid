package game;

import biuoop.KeyboardSensor;
import game.animation.AnimationRunner;
import game.auxiliary.Counter;
import game.features.EndScreen;
import game.features.KeyPressStoppableAnimation;
import game.features.WinningScreen;
import java.util.List;

/**
 * <h2>GameFlow Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score = new Counter(0);
    private boolean wonGame = true; //indicates wet

    /**
     * Constructor method.
     * @param ar an AnimationRunner
     * @param ks KeyboardSensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * This method runs all the levels in the given LevelInfo List.
     * @param levels a List.
     */

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, score);
            level.initialize();
            level.run();
            if (level.ballsLeft() <= 0) {
                animationRunner.run(
                        new KeyPressStoppableAnimation(
                                animationRunner.getKeyboardSensor(),
                                KeyboardSensor.SPACE_KEY,
                                new EndScreen(this.score.getValue())
                        )
                );
                this.wonGame = false; //game was lost if we got to this point
                break;
            }
        }
        //check if the user won - if he did, show a Winning screen.
        if (wonGame) {
            animationRunner.run(
                    new KeyPressStoppableAnimation(
                            animationRunner.getKeyboardSensor(),
                            KeyboardSensor.SPACE_KEY,
                            new WinningScreen(this.score.getValue())
                    )
            );
        }
    }
}
