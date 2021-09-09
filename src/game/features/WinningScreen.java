package game.features;

import biuoop.DrawSurface;
import game.animation.Animation;

import java.awt.Color;

/**
 * <h2>WinningScreen Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class WinningScreen implements Animation {
    private boolean stop;
    private int score;

    /**
     * Constructor method.
     * @param score the ending score
     */
    public WinningScreen(int score) {
        this.stop = false;
        this.score = score;
    }
    /**
     * Calls every Object registered as part of the animation to make single frame actions.
     * @param d a DrawSurface object
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.GRAY);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(150, d.getHeight() / 2 - 16, "You won! Your score is: " + score , 32);
    }

    /**
     * Indicates if the animation should stop or not.
     *
     * @return true if should, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
