package game.sprite;

import biuoop.DrawSurface;
/**
 * <h2>Sprite interface.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public interface Sprite {
    /**
     * Draws sprite on the screen.
     * @param d DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite Object that time has passed.
     */
    void timePassed();
}
