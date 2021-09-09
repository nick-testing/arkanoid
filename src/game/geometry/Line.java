package game.geometry;

import java.util.Random;
/**
 * <h2>Line Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class Line {
    private Point a;
    private Point b;
    /**
     * constructor in case given input is Points.
     * @param start starting point
     * @param end end point
     */
    public Line(Point start, Point end) {
        this.a = start;
        this.b = end;
    }
    /**
     * <h4>constructor in case given input is X and Y values of points.</h4>
     * @param x1 x value of start
     * @param y1 y value of start
     * @param x2 x value of end
     * @param y2 y value of end
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.a = new Point(x1, y1);
        this.b = new Point(x2, y2);
    }
    /**
     * calculates the length of the <b>Line</b>.
     * @return the length of the line
     */
    public double length() {
        return this.start().distance(this.end());
    }
    /**
     * calculates the middle <b>Point</b>.
     * @return point, the middle point of the line.
     */
    public Point middle() {
        double middleX = ((this.start()).getX() + (this.end()).getX()) / 2;
        double middleY = ((this.start()).getY() + (this.end()).getY()) / 2;
        return new Point(middleX, middleY);
    }
    /**
     * gets start point of the <b>Line</b>, determined by first given point.
     * @return the start point of the line
     */
    public Point start() {
        return this.a;
    }
    /**
     * gets end point of the <b>Line</b>, determined by second given point.
     * @return the end point of the line
     */
    public Point end() {
        return this.b;
    }
    /**
     * checks if two lines are intersecting or not.
     * @param other another Line
     * @return true if intersecting, false if not
     */
    public boolean isIntersecting(Line other) {
        //get the type of both lines - linear, single point or vertical
        int lineType1 = this.lineCheck();
        int lineType2 = other.lineCheck();
        //line represented by "this" is a point
        if (lineType1 == 1) {
            if (lineType2 == 1) {
                return this.start().equals(other.start());
            } else if (lineType2 == 0) {
                return (other.hasPoint(this.start())
                        && other.isPointOnLineEquation(this.start()));
            } else if (lineType2 == -1) {
                return this.hasIntersection(other);
            }
            return false;
        } else if (lineType1 == 0) {
            if (lineType2 == 1) {
                return this.hasPoint(other.start())
                        && this.isPointOnLineEquation(other.start());
            } else if (lineType2 == 0) {
                return this.isLineIntersectingWithLine(other);
            } else if (lineType2 == -1) {
                Point intersection = this.lineIntersectionWithVertical(other);
                return (this.hasPoint(intersection) && other.hasPoint(intersection));
            }
            return false;
        }
        //line represented by "this" can only be a vertical line
        if (lineType2 == 1) {
            return other.hasIntersection(this);
        } else if (lineType2 == 0) {
            Point intersection = other.lineIntersectionWithVertical(this);
            return (this.hasPoint(intersection) && other.hasPoint(intersection));
        } else if (lineType2 == -1) {
            return this.hasIntersection(other);
        }
        return false;
    }
    /**
     * calculates the intersection between two lines in different scenarios.
     * @param other another line
     * @return null if not intersecting, intersection point otherwise
     */
    public Point intersectionWith(Line other) {
        //checks if lines are intersecting
        if (!this.isIntersecting(other)) {
            return null;
        }
        //checks if parallel lines overlap
        if (this.isOverlappingWithParallel(other)) {
            return null;
        }
        //"this" is a point and is intersecting with the "other" line
        if (this.lineCheck() == 1) {
            return this.start();
        } else if (this.lineCheck() == 0) {
            //"this" follows a linear equation
            switch (other.lineCheck()) {
                case 1:
                    return other.start();
                case 0:
                    if (this.isOnSameLineParallel(other)) {
                        return this.verticalParallelLineIntersection(other);
                    }
                    return this.lineIntersectionWithLine(other);
                case -1:
                    return this.lineIntersectionWithVertical(other);
                default:
                    return null;
            }
        }
        //"this" can only be a vertical line, invokes relevant functions
        switch(other.lineCheck()) {
            case 1:
                return other.start();
            case 0:
                return other.lineIntersectionWithVertical(this);
            case -1:
                if (this.hasPointInsideVertical(other.start()) || this.hasPointInsideVertical(other.end())
                        || other.hasPointInsideVertical(this.start()) || other.hasPointInsideVertical(this.end())) {
                    return null;
                }
                return this.verticalParallelLineIntersection(other);
            default:
                return null;
        }
    }
    /**
     * checks if two given lines are identical.
     * @param other another Line
     * @return true if identical, false otherwise
     */
    public boolean equals(Line other) {
        if (this.start().equals(other.start())
                && this.end().equals(other.end())) {
            return true;
        }
        return (this.end().equals(other.start()) && this.start().equals(other.end()));
    }
    /**
     * Creates a random line with x and y values dependant on GUI resolution.
     * @param maxValX upper bound of x coordinate
     * @param maxValY upper bound of y coordinate
     * @return a randomly generated line
     */
    public static Line generateRandomLine(int maxValX, int maxValY) {
        Random rand = new Random();
        Point lineStart = new Point(rand.nextInt(maxValX) + 1, rand.nextInt(maxValY) + 1);
        Point lineEnd = new Point(rand.nextInt(maxValX) + 1, rand.nextInt(maxValY) + 1);
        return new Line(lineStart, lineEnd);
    }

    /**
     * returns the intersection Point with a rectangle that is closest to the start of the line.
     * @param rect rectangle object
     * @return closest intersection Point to the start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersectionArray = rect.intersectionPoints(this);
        if (intersectionArray.isEmpty()) {
            return null;
        }
        double minDistance = this.start().distance(intersectionArray.get(0));
        Point minPoint = intersectionArray.get(0);
        if (intersectionArray.size() > 1) {
            for (Point point : intersectionArray) {
                if (minDistance > this.start().distance(point)) {
                    minDistance = this.start().distance(point);
                    minPoint = point;
                }
            }
        }
        return minPoint;

    }


    //AUXILIARY METHODS FROM THIS POINT ON

    /**
     * checks if <b>Line</b> consists of two identical points.
     * @return true if in
     */
    private boolean isPoint() {
        return this.start().equals(this.end());
    }
    /**
     * checks if a <b>Line</b> is vertical.
     * @return true if is vertical, false otherwise
     */
    private boolean isVerticalLine() {
        return this.start().getX() == this.end().getX()
                && this.start().getY() != this.end().getY();
    }
    /**
     * checks if given <b>Line</b> is vertical or is a <b>Point</b>.
     * @return 1 if line is point, -1 if line is vertical,
     * 0 if line has a linear function
     */
    private int lineCheck() {
        if (this.isPoint()) {
            return 1;
        } else if (this.isVerticalLine()) {
            return -1;
        }
        return 0;
    }
    /**
     * Method for calculating slope of <b>Line</b>.
     * @return the slope
     */
    private double calcSlope() {
        double slope;
        slope = (this.start().getY() - this.end().getY())
                / (this.start().getX() - this.end().getX());
        return slope;
    }
    /**
     * calculates offset of <b>Line</b> function y=mx+b.
     * @param slope slope, calculated by calcSlope
     * @return offset of line function
     */
    private double calcOffset(double slope) {
        double offset;
        offset = this.start().getY() - (slope * this.start().getX());
        return offset;
    }
    /**
     * checks if two non-vertical <b>Lines</b> intersect.
     * @param other another line
     * @return true if intersect, false otherwise
     */
    private boolean hasIntersection(Line other) {
        double maxValX = Math.max(other.start().getX(), other.end().getX());
        double minValX = Math.min(other.start().getX(), other.end().getX());
        double maxValY = Math.max(other.start().getY(), other.end().getY());
        double minValY = Math.min(other.start().getY(), other.end().getY());
        double startX = this.start().getX();
        double endX = this.end().getX();
        double startY = this.start().getY();
        double endY = this.end().getY();
        return (((startX >= minValX && startX <= maxValX)
                && (startY >= minValY && startY <= maxValY))
                || ((endX >= minValX && endX <= maxValX)
                && (endY >= minValY && endY <= maxValY)));
    }
    /**
     * checks if two <b>Lines</b> are parallel.
     * @param other another line
     * @return true if parallel, false otherwise
     */
    private boolean isParallel(Line other) {
        return this.calcSlope() == other.calcSlope();
    }
    /**
     * checks if two parallel <b>Lines</b> share the same linear function.
     * @param other another line
     * @return true if parallel and are on the same line, false otherwise.
     */
    private boolean isOnSameLineParallel(Line other) {
        double offsetA = this.calcOffset(this.calcSlope());
        double offsetB = other.calcOffset(other.calcSlope());
        return (isParallel(other) && offsetA == offsetB);
    }
    /**
     * checks if given <b>Line</b> contains a given <b>Point</b>.
     * @param other point.
     * @return true false
     */
    private boolean hasPoint(Point other) {
        double maxValX = Math.max(this.start().getX(), this.end().getX());
        double minValX = Math.min(this.start().getX(), this.end().getX());
        double maxValY = Math.max(this.start().getY(), this.end().getY());
        double minValY = Math.min(this.start().getY(), this.end().getY());
        return ((other.getX() >= minValX && other.getX() <= maxValX)
                && (other.getY() >= minValY && other.getY() <= maxValY));
    }
    /**
     * checks if given <b>Line</b> contains a point within it's bounds.
     * @param other a Point
     * @return true if within bounds, false otherwise
     */
    private boolean hasPointInside(Point other) {
        double maxValX = Math.max(this.start().getX(), this.end().getX());
        double minValX = Math.min(this.start().getX(), this.end().getX());
        double maxValY = Math.max(this.start().getY(), this.end().getY());
        double minValY = Math.min(this.start().getY(), this.end().getY());
        return ((other.getX() > minValX && other.getX() < maxValX)
                || (other.getY() > minValY && other.getY() < maxValY));
    }
    /**
     * checks if given vertical <b>Line</b> contains a point within it's bounds.
     * @param other Point
     * @return true if Point is on the vertical line
     */
    private boolean hasPointInsideVertical(Point other) {
        double lineValX = this.start().getX();
        double maxValY = Math.max(this.start().getY(), this.end().getY());
        double minValY = Math.min(this.start().getY(), this.end().getY());
        //checks if x value is identical and y value of point is in range
        return (other.getX() == lineValX && (other.getY() > minValY
                && other.getY() < maxValY));
    }
    /**
     * checks if <b>Point</b> is on another <b>Line</b>.
     * @param other Point
     * @return true if fits line's linear equation, false otherwise
     */
    private boolean isPointOnLineEquation(Point other) {
        double slope = this.calcSlope();
        double offset = this.calcOffset(slope);
        return (other.getY() == other.getX() * slope + offset);
    }
    /**
     * checks if <b>Line</b> is intersecting with another <b>Line</b>.
     * @param other another Line
     * @return true if intersect, false otherwise
     */
    private boolean isLineIntersectingWithLine(Line other) {
        double slopeA = this.calcSlope();
        double offsetA = this.calcOffset(slopeA);
        double slopeB = other.calcSlope();
        double offsetB = other.calcOffset(slopeB);
        if (slopeA == slopeB && offsetA != offsetB) {
            return false;
        } else if (slopeA == slopeB && offsetA == offsetB) {
            return this.hasIntersection(other);
        }
        Point intersection = this.lineIntersectionWithLine(other);
        return (this.hasPoint(intersection) && other.hasPoint(intersection));
    }
    /**
     * checks if two parallel <b>Lines</b> overlap.
     * @param other a line parallel to the other one
     * @return true if overlap, false otherwise if not parallels
     *  or do not overlap
     */
    private boolean isOverlappingWithParallel(Line other) {
        return (this.isOnSameLineParallel(other)
                && (this.hasPointInside(other.start()) || this.hasPointInside(other.end())
                || other.hasPointInside(this.start()) || other.hasPointInside(this.end())
                || (this.start().equals(other.start()) && this.end().equals(other.end()))
                || (this.start().equals(other.end()) && this.end().equals(other.start()))));
    }
    /**
     * calculates intersection between vertical and normal <b>Lines</b>.
     * <p>assumes Object from which it was called is a normal line.
     * @param other another Line
     * @return Point of intersection
     */
    private Point lineIntersectionWithVertical(Line other) {
        double slope = this.calcSlope();
        double offset = this.calcOffset(slope);
        double intersectionX = other.start().getX();
        double intersectionY = Math.floor((slope * intersectionX + offset) * 100) / 100;
        return new Point(intersectionX, intersectionY);
    }
    /**
     * calculates intersection between two linear, nonparallel <b>Lines.</b>
     * @param other another Line
     * @return Point of intersection
     */
    private Point lineIntersectionWithLine(Line other) {
        double slopeA = this.calcSlope();
        double offsetA = this.calcOffset(slopeA);
        double slopeB = other.calcSlope();
        double offsetB = other.calcOffset(slopeB);
        double intersectionX = (offsetB - offsetA) / (slopeA - slopeB);
        double intersectionY = intersectionX * slopeA + offsetA;
        intersectionX = Math.floor(intersectionX * 100) / 100;
        intersectionY = Math.floor(intersectionY * 100) / 100;
        return new Point(intersectionX, intersectionY);
    }
    /**
     * calculates intersection between two Parallels on the same line(if exists) or vertical <b>Lines</b>.
     * <p>assumes they are on the same linear function.
     * @param other another Line
     * @return Point of intersection
     */
    private Point verticalParallelLineIntersection(Line other) {
        Point startA = this.start();
        Point endA = this.end();
        Point startB = other.start();
        Point endB = other.end();
        if (startA.equals(startB)) {
            return startA;
        } else if (endA.equals(startB)) {
            return endA;
        }
        return endB;
    }
}
