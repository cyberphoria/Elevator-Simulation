package requests;

/**
 * SubsystemPasser enables receiving and sending of various SystemEvents from
 * subsystem components to subsystems.
 *
 * @author Liam Tripp
 */
public interface SubsystemPasser {

    /**
     * Passes an ApproachEvent to the SubsystemPasser's Subsystem.
     *
     * @param approachEvent the ApproachEvent to be passed to the subsystem
     */
    void passApproachEvent(ApproachEvent approachEvent);

    /**
     * Receives an ApproachEvent from the Subsystem and returns it to the component.
     *
     * @param approachEvent the ApproachEvent to be received from the Subsystem
     */
    void receiveApproachEvent(ApproachEvent approachEvent);
}
