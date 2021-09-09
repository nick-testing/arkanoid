package game.collidable.object;

import biuoop.DrawSurface;
import game.GameLevel;
import game.collidable.Collidable;
import game.collidable.logic.HitListener;
import game.collidable.logic.HitNotifier;
import game.geometry.Ball;
import game.geometry.Rectangle;
import game.geometry.Point;
import game.geometry.Line;
import game.geometry.properties.Velocity;
import game.sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Block class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color = Color.GRAY;
    private List<HitListener> listeners = new ArrayList<HitListener>();

    /**
     * Constructor that receives a Rectangle object and creates a Block object out of it.
     * @param rect Rectangle object
     */
    public Block(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * Constructor that receives a Rectangle object and creates a Block object out of it.
     * @param rect Rectangle object
     * @param color Block color
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }

    /**
     * Gets the color field of the current object.
     * @return Color of Ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * returns the "collision shape" of the Object.
     * @return the shape of the object
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    /**
     * changes Velocity upon collision with Block.
     * @param hitter the Ball that hits
     * @param collisionPoint point of collision
     * @param currentVelocity velocity before collision
     * @return velocity post collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        notifyHit(hitter);
        Line collisionPointToLine = new Line(collisionPoint, collisionPoint);
        boolean horizontalFlg = false;
        boolean verticalFlg = false;
        for (int i = 0; i < this.rect.getLineArray().length; ++i) {
            if (i < 2 && !horizontalFlg) { // i is on horizontal line indexes
                horizontalFlg = this.rect.getLineArray()[i].isIntersecting(collisionPointToLine);
            } else if (i >= 2 && !verticalFlg) { // i is 2 or 3, vertical lines
                verticalFlg = this.rect.getLineArray()[i].isIntersecting(collisionPointToLine);
            }
        }
        // checks if collision happens with a horizontal line
        if (horizontalFlg) {
            //checks if collision is on edge
            if (verticalFlg) {
                return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            }
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (verticalFlg) { // collision is on a vertical line
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return currentVelocity;


    }

    /**
     * Draws a Block.
     * @param d DrawSurface object to which it'll draw a block
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor());
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * executes command after frame of reference time has passed.
     */
    @Override
    public void timePassed() {
        // do nothing
    }

    /**
     * adds the current object to the Game, adds both to Sprite and Collidable fields.
     * @param g Game object
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removes the current Block from the game.
     * @param gameLevel Game class
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Notifies all Listeners that a hit has occurred.
     * @param hitter the Ball that hits
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listenerLs = new ArrayList<HitListener>(this.listeners);
        // Notify all listeners about a hit event
        for (HitListener hl : listenerLs) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * adds a Listener to the hit events.
     * @param hl a hitListener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.listeners.add(hl);
    }

    /**
     * removes a Listener from the hit events.
     * @param hl a hitListener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.listeners.remove(hl);
    }
}
