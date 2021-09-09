package game.collidable.logic;

/**
 * <h2>HitNotifier interface.</h2>
 * <p>
 * @author NickVoz
 * <p>
 */
public interface HitNotifier {

    /**
     * adds a Listener to the hit events.
     * @param hl a hitListener
     */
    void addHitListener(HitListener hl);

    /**
     * removes a Listener from the hit events.
     * @param hl a hitListener
     */
    void removeHitListener(HitListener hl);
}
