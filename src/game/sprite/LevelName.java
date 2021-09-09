package game.sprite;

import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Color;

/**
 * <h2>Level1 Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class LevelName implements Sprite {
    private String levelName;

    /**
     * Constructor method.
     * @param name a String representing the name;
     */
    public LevelName(String name) {
        this.levelName = name;
    }
    /**
     * Draws sprite on the screen.
     *
     * @param d DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(5, 20, "Level Name: " + levelName, 15);
    }

    /**
     * Notifies the sprite Object that time has passed.
     */
    @Override
    public void timePassed() {
        //do nothing
    }

    /**
     * adds the object to the Sprite list of the GameLevel object.
     * @param gameLevel a GameLevel object
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
