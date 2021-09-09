package game.features;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.animation.Animation;

/**
 * <h2>KeyPressStoppableAnimation Class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private Animation animation;
    private boolean stop;
    private String actionKey;
    private boolean isAlreadyPressed = true;

    /**
     * Constructor method.
     * @param sensor KeyboardSensor object
     * @param key input key to resume operation
     * @param a an animation to wrap
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation a) {
        this.keyboard = sensor;
        actionKey = key;
        this.animation = a;
        this.stop = false;
    }
    /**
     * Calls every Object registered as part of the animation to make single frame actions.
     *
     * @param d a DrawSurface object
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (!this.keyboard.isPressed(actionKey)) {
            this.isAlreadyPressed = false;
        }
        if (this.keyboard.isPressed(actionKey) && !isAlreadyPressed) {
            this.stop = true;
        }
    }

    /**
     * Indicates if the animation should stop or not.
     *
     * @return true if should, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
