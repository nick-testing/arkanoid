package game.geometry.properties;

import game.geometry.Point;

/**
 * <h2>Velocity Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor method.
     * @param dx velocity over the x axis.
     * @param dy velocity over the y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * gets Dx value of current Velocity object.
     * @return dx value of Velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * gets Dx value of current Velocity object.
     * @return dx value of Velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * calculates coordinates of a point for the next point of reference.
     * @param p coordinates
     * @return Point with applied location change.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.getDx(), p.getY() + this.getDy());
    }

    /**
     * Creates a velocity object from given Angle and speed values.
     * @param angle double, direction of velocity compared to the origin
     * @param speed total value of Velocity
     * @return Velocity object, calculated through speed and angle.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        return new Velocity(Math.cos(Math.toRadians(270 + angle)) * speed,
                Math.sin(Math.toRadians(270 + angle)) * speed);
    }
}
