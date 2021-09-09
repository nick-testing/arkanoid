package game;

import game.collidable.Collidable;
import game.collidable.CollisionInfo;
import game.geometry.Point;
import game.geometry.Line;

import java.util.List;
import java.util.ArrayList;

/**
 * <h2>GameEnvironment Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class GameEnvironment {

    private List<Collidable> collidableList = new ArrayList<>();

    /**
     * @return list of Collidable objects
     */
    public List<Collidable> getCollidableList() {
        return this.collidableList;
    }

    /**
     * adds a Collidable object to the collidable List.
     * @param c Collidable object
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    /**
     * The function finds which Collidable object will collide the closest to the trajectory starting point.
     * @param trajectory Line which represents velocity vector in one frame of reference.
     * @return CollisionInfo of the collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        boolean isMultipleCollisions = false;
        Point collisionPoint = null; // represents current closest collision point
        Collidable collisionObject = null;
        // calculates collision Point
        for (Collidable i : collidableList) {
            // current intersection
            Point intersectionPoint = trajectory.closestIntersectionToStartOfLine(i.getCollisionRectangle());
            if (intersectionPoint != null) { // checks for intersection with current collidable
                if (!isMultipleCollisions) {
                    isMultipleCollisions = true;
                    collisionPoint = intersectionPoint;
                    collisionObject = i;
                    continue;
                }
                // checks if distance from trajectory of starting point is closer to current or previous intersection
                if (trajectory.start().distance(collisionPoint) > trajectory.start().distance(intersectionPoint)) {
                    collisionPoint = intersectionPoint;
                    collisionObject = i;
                }
            }
        }
        if (!isMultipleCollisions || collisionPoint == null) {
            return null;
        }
        return new CollisionInfo(collisionPoint, collisionObject);
    }

    /**
     * removes a Collidable object from the Collidable list.
     * @param c a Collidable object
     */
    public void remove(Collidable c) {
        collidableList.remove(c);
    }

    /**
     * Gets current number of Collidables in the environment.
     * @return size of environment
     */
    public int getEnvSize() {
        return this.collidableList.size();
    }
}
