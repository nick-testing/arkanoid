package game.sprite;

import biuoop.DrawSurface;
import java.util.List;
import java.util.ArrayList;

/**
 * <h2>SpriteCollection Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class SpriteCollection {
    private List<Sprite> spriteList = new ArrayList<Sprite>();

    /**
     * adds a Sprite to the sprite list.
     * @param s Sprite object
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * notifies all Sprites that time has passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copy = new ArrayList<Sprite>(spriteList);
        for (Sprite i : copy) {
            i.timePassed();
        }
    }

    /**
     * Draws all sprites on the DrawSruface.
     * @param d DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> copy = new ArrayList<Sprite>(spriteList);
        for (Sprite i : copy) {
            i.drawOn(d);
        }
    }

    /**
     * removes a sprite from the Sprite list.
     * @param s Sprite to be removed
     */
    public void remove(Sprite s) {
        spriteList.remove(s);
    }
}
