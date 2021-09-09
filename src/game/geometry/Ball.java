package game.geometry;

import biuoop.DrawSurface;
import game.GameLevel;
import game.GameEnvironment;
import game.collidable.CollisionInfo;
import game.geometry.properties.Velocity;
import game.sprite.Sprite;

import java.awt.Color;

/**
 * <h2>Ball Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class Ball implements Sprite {
    private Point center;
    private int r = BALL_RADIUS;
    private Color color = Color.WHITE;
    private Velocity velocity = new Velocity(0, 0);
    private GameEnvironment env = null;
    public static final int BALL_RADIUS = 5;

    // CONSTRUCTOR AND FIELD ALTERING METHODS

    /**
     * Constructor in case center point is given as a point.
     * @param center Point object
     * @param r int, ball radius
     * @param color Color object, gets ball color
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * constructor that gets x and y as doubles.
     * @param x double
     * @param y double
     * @param r radius of Ball
     * @param color Color of Ball
     */
    public Ball(double x, double y, int r, Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * Constructor in case center point is given as a point and velocity.
     * @param center Point object
     * @param r ball radius
     * @param color Color of ball
     * @param velocity Velocity of Ball.
     */
    public Ball(Point center, int r, Color color, Velocity velocity) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = velocity;
    }

    /**
     * sets the GameEnvironment of the ball.
     * @param environment object.
     */
    public void setEnvironment(GameEnvironment environment) {
        this.env = environment;
    }

    /**
     * Sets the velocity field of the Ball.
     * @param v Velocity object
     */
    public void setVelocity(Velocity v) {
        if (v == null) {
            System.out.println("Velocity can't be null!");
        } else {
            this.velocity = v;
        }
    }

    /**
     * sets the velocity field of the Ball using double values.
     * @param dx x axis velocity value
     * @param dy y axis velocity value
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    // METHODS FOR RECEIVING DATA FROM FIELDS

    /**
     * Gets the x coordinate of the center point of the Ball in order to draw it.
     * @return x value of center
     */
    private int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets the y coordinate of the center point of the Ball in order to draw it.
     * @return y value of center
     */
    private int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets the radius field of the current object.
     * @return r value of Ball
     */
    public int getSize() {
       return this.r;
    }

    /**
     * Gets the color field of the current object.
     * @return Color of Ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Gets the current velocity of the object.
     * @return Velocity of Ball.
     */
    private Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * calculates Ball trajectory.
     * @return Line representing ball trajectory in current frame of reference
     */
    private Line calculateTrajectory() {
        return new Line(this.center,
                new Point(this.center.getX() + this.velocity.getDx(), this.center.getY() + this.velocity.getDy()));
    }

    /**
     * Moves the Ball one step on the GUI surface, in accordance with the Ball's current position.
     */
    public void moveOneStep() {
        CollisionInfo colInfo = this.env.getClosestCollision(this.calculateTrajectory());
        double velocityDx = this.velocity.getDx();
        double velocityDy = this.velocity.getDy();
        if (colInfo == null) {
            // checks if ball bounces out of the GUI border
            if (this.center.getX() + velocityDx < 0) {
                if (this.center.getY() + velocityDy < 0) {
                    this.center = new Point(this.getSize(), this.getSize());
                    this.velocity = new Velocity(-velocityDx, -velocityDy);
                } else {
                    this.center = new Point(this.getSize(), this.center.getY() + this.velocity.getDy());
                    this.velocity = new Velocity(-velocityDx, velocityDy);
                }
            } else if (this.center.getY() + velocityDy < 0) {
                this.center = new Point(this.center.getX() + velocityDx, this.getSize());
                this.velocity = new Velocity(velocityDx, -velocityDy);
            } else if (this.center.getX() + velocityDx > GameLevel.GUI_DIMENSION_X) {
                if (this.center.getY() + velocityDy > GameLevel.GUI_DIMENSION_Y) {
                    this.center = new Point(GameLevel.GUI_DIMENSION_X - this.getSize(),
                            GameLevel.GUI_DIMENSION_Y - this.getSize());
                    this.velocity = new Velocity(-velocityDx, -velocityDy);
                } else {
                    this.center = new Point(GameLevel.GUI_DIMENSION_X - this.getSize()
                            , this.center.getY() + this.velocity.getDy());
                    this.velocity = new Velocity(-velocityDx, velocityDy);
                }
            } else if (this.center.getY() + velocityDy > GameLevel.GUI_DIMENSION_Y) {
                this.center = new Point(this.center.getX() + velocityDx, GameLevel.GUI_DIMENSION_Y - this.getSize());
                this.velocity = new Velocity(velocityDx, -velocityDy);
            } else {
                this.center = this.getVelocity().applyToPoint(this.center);
            }
        } else {
            // applies post-hit velocity
            this.setVelocity(colInfo.collisionObject().hit(this, colInfo.collisionPoint(), this.velocity));
            switch (colInfo.getIntersectionPointLineLocation()) {
                case 0:
                    this.center = new Point(colInfo.collisionPoint().getX(),
                            colInfo.collisionPoint().getY() - this.getSize());
                    break;
                case 1:
                    this.center = new Point(colInfo.collisionPoint().getX(),
                            colInfo.collisionPoint().getY() + this.getSize());
                    break;
                case 2:
                    this.center = new Point(colInfo.collisionPoint().getX() - this.getSize(),
                            colInfo.collisionPoint().getY());
                    break;
                case 3:
                    this.center = new Point(colInfo.collisionPoint().getX() + this.getSize(),
                            colInfo.collisionPoint().getY());
                    break;
                default:
                    this.center = colInfo.collisionPoint();
                    break;
            }

        }
    }
    /**
     * Draws a circle.
     * @param d DrawSurface object to which it'll draw a circle
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawCircle(this.getX(), this.getY(), this.getSize());
        d.setColor(this.getColor());
        d.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * executes command after frame of reference time has passed.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Adds the current Ball to the Game object, adds only to the Sprite field.
     * @param g Game object
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        this.setEnvironment(g.getEnv());
    }

    /**
     * removes the current Ball from the Game.
     * @param g a Game Class
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
