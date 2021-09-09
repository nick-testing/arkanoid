package game.sprite;

import biuoop.DrawSurface;
import game.GameLevel;
import game.auxiliary.Counter;

import java.awt.Color;

/**
 * <h2>ScoreIndicator Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    /**
     * Constructor method.
     * @param score Counter for current score
     */
    public ScoreIndicator(Counter score) {
        this.scoreCounter = score;
    }

    /**
     * Draws sprite on the screen.
     *
     * @param d DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(375, 20, "Score: " + scoreCounter.getValue(), 15);
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
     * @param gameLevel game to which the ScoreIndicator should be added
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
