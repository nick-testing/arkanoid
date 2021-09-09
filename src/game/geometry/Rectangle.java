package game.geometry;

/**
 * <h2>Rectangle Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line[] lineArray = new Line[4]; // Array containing all Rectangle lines, horizontal first.

    /**
     * constructor in case upper left point was given as a Point.
     * @param upperLeft upper left Point of Rectangle
     * @param width of rectangle
     * @param height of rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        double x = upperLeft.getX();
        double y = upperLeft.getY();
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
        this.lineArray[0] = new Line(x, y, x + width, y);
        this.lineArray[1] = new Line(x, y + height, x + width, y + height);
        this.lineArray[2] = new Line(x, y, x, y + height);
        this.lineArray[3] = new Line(x + width, y, x + width, y + height);
    }

    /**
     * constructor in case upper left point was given as two coordinates.
     * @param x x value of upper left Point
     * @param y y value of upper left Point
     * @param width of rectangle
     * @param height of rectangle
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
        // initializes array of Lines,
        this.lineArray[0] = new Line(x, y, x + width, y);
        this.lineArray[1] = new Line(x, y + height, x + width, y + height);
        this.lineArray[2] = new Line(x, y, x, y + height);
        this.lineArray[3] = new Line(x + width, y, x + width, y + height);
    }

    /**
     * calculates intersections between given line and rectangle, removes duplicate intersections.
     * @param line line to calculate intersection with
     * @return List of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // creates an ArrayList containing all intersection points, including duplicates.
        java.util.List<Point> intersectionArray = this.getIntersectionsRaw(line);
        //deletes duplicate intersection points.
        for (int i = 0; i < intersectionArray.size(); ++i) {
            for (int j = i + 1; j < intersectionArray.size(); ++j) {
                if (intersectionArray.get(i).equals(intersectionArray.get(j))) {
                    intersectionArray.remove(j);
                    j--;

                }
            }
        }
        return intersectionArray;
    }

    /**
     * calculates ALL intersections of the current rectangle with a given line.
     * @param line line to calculate intersection with
     * @return List of all intersection points, with possible duplicates
     */
    private java.util.List<Point> getIntersectionsRaw(Line line) {
        java.util.List<Point> intersectionArray = new java.util.ArrayList<Point>();
        for (Line i : this.lineArray) {
            if (i.intersectionWith(line) != null) {
                intersectionArray.add(i.intersectionWith(line));
            }
        }
        return intersectionArray;
    }

    /**
     * @return height of rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return width of rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return upper left Point of of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return array of lines representing the Rectangle, first vertical, then horizontal.
     */
    public Line[] getLineArray() {
        return this.lineArray;
    }

}
