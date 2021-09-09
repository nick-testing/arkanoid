package game.collidable.object;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import game.collidable.Collidable;
import game.geometry.Ball;
import game.geometry.Rectangle;
import game.geometry.Point;
import game.geometry.Line;
import game.geometry.properties.Velocity;
import game.sprite.Sprite;

import java.awt.Color;

/**
 * <h2>Paddle Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle rect;
    private Color color = Color.YELLOW;
    public static final int PADDLE_HEIGHT = 30;
    private final int speed;

    /**
     * Constructor methods, receives KeyboardSensor, Rectangle.
     * @param keyboard KeyboardSensor
     * @param rect Rectangle
     * @param velocity x axis speed of Paddle
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rect, int velocity) {
        this.keyboard = keyboard;
        this.rect = rect;
        this.speed = velocity;
    }

    /**
     * moves the Paddle left, if possible.
     */
    public void moveLeft() {
        double currentUpperLeftX = this.rect.getUpperLeft().getX();
        double currentUpperLeftY = this.rect.getUpperLeft().getY();
        double width = this.rect.getWidth();
        double height = this.rect.getHeight();
        if (currentUpperLeftX - this.speed >= 30) {
            this.rect = new Rectangle(new Point(currentUpperLeftX - this.speed, currentUpperLeftY), width, height);
        } else {
            this.rect = new Rectangle(
                    new Point(GameLevel.BORDER_WIDTH , currentUpperLeftY), width, height
            );
        }
    }

    /**
     * moves the Paddle right, if possible.
     */
    public void moveRight() {
        double currentUpperLeftX = this.rect.getUpperLeft().getX();
        double currentUpperLeftY = this.rect.getUpperLeft().getY();
        double width = this.rect.getWidth();
        double height = this.rect.getHeight();
        if (currentUpperLeftX + rect.getWidth() + this.speed <= GameLevel.GUI_DIMENSION_X - GameLevel.BORDER_WIDTH) {
            this.rect = new Rectangle(new Point(currentUpperLeftX + this.speed, currentUpperLeftY), width, height);
        } else {
            this.rect = new Rectangle(
                    new Point(GameLevel.GUI_DIMENSION_X - GameLevel.BORDER_WIDTH - width, currentUpperLeftY),
                    width,
                    height
            );
        }
    }

    /**
     * @return the Shape of the object
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * changes Velocity upon collision with different areas of Paddle.
     * @param hitter the Ball that hits
     * @param collisionPoint  Point of collision
     * @param currentVelocity current velocity of ball
     * @return new velocity value
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line collisionPointToLine = new Line(collisionPoint, collisionPoint);
        Line[] segmentedLine = this.createSegmentedLine();
        int i;
        for (i = 0; i < segmentedLine.length; i++) {
            if (collisionPointToLine.isIntersecting(segmentedLine[i])) {
                break;
            }
        }
        if (i == 0) {
            return Velocity.fromAngleAndSpeed(300, 11);
        } else if (i == 1) {
            return Velocity.fromAngleAndSpeed(330, 10);
        } else if (i == 2) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (i == 3) {
            return Velocity.fromAngleAndSpeed(30, 10);
        } else if (i == 4) {
            return Velocity.fromAngleAndSpeed(60, 11);
        }
        return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
    }

    /**
     * Splits line into five different Lines in a single array.
     * @return Segmented line array
     */
    private Line[] createSegmentedLine() {
        Line rectHorizontalLine = this.rect.getLineArray()[0]; // Line where collision occurs
        double yVal = rectHorizontalLine.start().getY();
        double fifthLength = rect.getWidth() / 5;
        Line[] segmentedLine = new Line[5];
        segmentedLine[0] = new Line(rectHorizontalLine.start().getX(), yVal,
                rectHorizontalLine.start().getX() + fifthLength, yVal);
        for (int i = 1; i < segmentedLine.length; i++) {
            segmentedLine[i] = new Line(segmentedLine[i - 1].end().getX(), yVal,
                    segmentedLine[i - 1].end().getX() + fifthLength, yVal);
        }
        return segmentedLine;
    }

    /**
     * Draws sprite on the screen.
     * @param d DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * Notifies the sprite Object that time has passed.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * adds current Paddle to the Game.
     * @param g Game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
