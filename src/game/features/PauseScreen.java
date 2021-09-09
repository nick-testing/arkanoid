package game.features;

import biuoop.DrawSurface;
import game.animation.Animation;

import java.awt.Color;


/**
 * <h2>PauseScreen Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * Constructor method.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * Calls every Object registered as part of the animation to make single frame actions.
     *
     * @param d a DrawSurface object
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.GRAY);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(150, d.getHeight() / 2 - 16, "paused -- press space to continue", 32);
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
