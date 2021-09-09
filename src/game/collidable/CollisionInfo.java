package game.collidable;

import game.geometry.Point;
import game.geometry.Line;

/**
 * <h2>CollisionInfo Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor method.
     * @param a Point of collision
     * @param obj object that collided
     */
    public CollisionInfo(Point a, Collidable obj) {
        this.collisionPoint = a;
        this.collisionObject = obj;
    }

    /**
     * gets the collision Point.
     * @return Point of collision
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * gets the collision object.
     * @return collision Object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * method finds which edge of the rectangle collides with the Ball.
     * @return index of colliding edge in the lineArray of Rectangle
     */
    public int getIntersectionPointLineLocation() {
        Line[] lineArray = this.collisionObject.getCollisionRectangle().getLineArray();
        for (int i = 0; i < lineArray.length; ++i) {
            if (lineArray[i].isIntersecting(new Line(this.collisionPoint, this.collisionPoint))) {
                return i;
            }
        }
        return -1;
    }
}
