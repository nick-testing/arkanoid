package game.sprite;

import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Color;

/**
 * <h2>Oval Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class Oval implements Sprite {
    private final Color color;
    private final int i1;
    private final int i2;
    private final int i3;
    private final int i4;

    /**
     * Constructor method.
     * @param i1 x value of middle point
     * @param i2 y value of middle point
     * @param i3 height
     * @param i4 width
     * @param c color
     */
    public Oval(int i1, int i2, int i3, int i4, Color c) {
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;
        this.i4 = i4;
        this.color = c;
    }
    /**
     * Draws sprite on the screen.
     *
     * @param d DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillOval(i1, i2, i3, i4);
    }

    /**
     * Notifies the sprite Object that time has passed.
     */
    @Override
    public void timePassed() {
        //do nothing
    }

    /**
     * adds the current Object to a Game.
     * @param g game to which the ScoreIndicator should be added
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
