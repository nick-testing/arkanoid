package game.collidable;

import game.geometry.Ball;
import game.geometry.Point;
import game.geometry.Rectangle;
import game.geometry.properties.Velocity;
import game.GameLevel;

/**
 * <h2>Colllidable interface.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public interface Collidable {

    /**
     * @return the Shape of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * changes Velocity upon collision with object.
     * @param hitter the Ball that hits
     * @param collisionPoint Point of collision
     * @param currentVelocity current velocity of ball
     * @return new velocity value
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * adds Collidable object to the given Game.
     * @param g Game
     */
    void addToGame(GameLevel g);
}
