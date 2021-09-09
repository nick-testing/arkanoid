package game.geometry;

/**
 * <h2> Point class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class Point {
    private double x;
    private double y;
    public static final double EPSILON = 0.000000000000001;
    /**
     * @param x double, y coordinate of point
     * @param y double, y coordinate of point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * @param other - another object from the point class
     * @return distance between this and another point
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }
    /**
     * @param other another Point
     * @return true if same point, false otherwise.
     */
    public boolean equals(Point other) {
        boolean xRange = this.getX() >= other.getX() - EPSILON && this.getX() <= other.getX() + EPSILON;
        boolean yRange = this.getY() >= other.getY() - EPSILON && this.getY() <= other.getY() + EPSILON;
        return xRange && yRange;
    }
    /**
     * @return x value of point
     */
    public double getX() {
        return this.x;
    }
    /**
     * @return y value of point
     */
    public double getY() {
        return this.y;
    }

}
