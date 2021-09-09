package game.auxiliary;

/**
 * <h2>Counter class.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public class Counter {
    private int currentValue;

    /**
     * Constructor method.
     * @param x initial counter value
     */
    public Counter(int x) {
        currentValue = x;
    }
    /**
     * increases the counter by a give value.
     * @param number an integer
     */
    public void increase(int number) {
        currentValue += number;
    }

    /**
     * decreases the counter by a given value.
     * @param number an integer
     */
    public void decrease(int number) {
        currentValue -= number;
    }

    /**
     * Returns current value field.
     * @return int the current value
     */
    public int getValue() {
        return currentValue;
    }
}
