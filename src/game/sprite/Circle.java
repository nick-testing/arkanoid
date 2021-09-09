package game.sprite;

import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Color;

/**
 * <h2>Circle Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class Circle implements Sprite {
    private final int centerX;
    private final int centerY;
    private final int radius;
    private final Color color;

    /**
     * Constructor method.
     * @param x desired center point x value
     * @param y desired center point y value
     * @param r desired radius
     * @param c desired color
     */
    public Circle(int x, int y, int r, Color c) {
        this.centerX = x;
        this.centerY = y;
        this.radius = r;
        this.color = c;
    }

    /**
     * Draws sprite on the screen.
     *
     * @param d DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillCircle(centerX, centerY, radius);
    }

    /**
     * Notifies the sprite Object that time has passed.
     */
    @Override
    public void timePassed() {
        //do nothing
    }
    /**
     * Adds the current Object to the game.
     * @param g Game object
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
