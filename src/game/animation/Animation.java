package game.animation;

import biuoop.DrawSurface;

/**
 * <h2>Animation interface.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public interface Animation {

    /**
     * Calls every Object registered as part of the animation to make single frame actions.
     * @param d a DrawSurface object
     */
    void doOneFrame(DrawSurface d);

    /**
     * Indicates if the animation should stop or not.
     * @return true if should, false otherwise
     */
    boolean shouldStop();
}
