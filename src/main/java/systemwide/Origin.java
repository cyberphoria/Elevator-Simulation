package systemwide;

/**
 * Origin is an enum representing the Runnable system from which Requests come from.
 *
 * @author Liam Tripp
 */
public enum Origin {
    FLOOR_SYSTEM,
    ELEVATOR_SYSTEM,
    SCHEDULER;

    /**
     * Provides an Origin in the opposite direction of the Origin provided.
     *
     * @param origin the Origin provided
     * @return an Origin in the opposite direction of the Origin provided.
     */
    public static Origin changeOrigin(Origin origin) {
        if (origin == FLOOR_SYSTEM) {
            return ELEVATOR_SYSTEM;
        } else if (origin == ELEVATOR_SYSTEM){
            return FLOOR_SYSTEM;
        } else {
            throw new IllegalArgumentException("Error: Trying to change origin when origin is Scheduler.");
        }
    }
}