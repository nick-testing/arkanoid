package game.animation;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;

import java.awt.Color;


/**
 * <h2>AnimationRunner Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();

    /**
     * Constructor method.
     * @param otherGui receives a GUI object with a name and dimensions
     * @param fps frames per second
     */
    public AnimationRunner(GUI otherGui, int fps) {
        this.gui = otherGui;
        this.framesPerSecond = fps;
    }

    /**
     * @return the GUI KeyboardSensor field
     */
    public KeyboardSensor getKeyboardSensor() {
        return this.gui.getKeyboardSensor();
    }

    /**
     * Executes the animation loop of the game, will stop when ending condition is reached.
     * @param animation Animation object being run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, 800, 600);
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        return;
    }
}
