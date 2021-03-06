package floorsystem;

import requests.ApproachEvent;
import requests.SubsystemPasser;

/**
 * Floor simulates a level of a structure that an elevator can visit.
 *
 * @author Liam Tripp
 */
public class Floor implements SubsystemPasser {

    private final int floorNumber;
    private final FloorSubsystem floorSubsystem;
    private final ArrivalSensor arrivalSensor;

    public Floor(int floorNumber, FloorSubsystem floorSubsystem) {
        this.floorNumber = floorNumber;
        this.floorSubsystem = floorSubsystem;
        arrivalSensor = new ArrivalSensor(floorNumber);
    }

    /**
     * Passes an ApproachEvent to the corresponding subsystem.
     *
     * @param approachEvent the ApproachEvent that is examined by the ArrivalSensor
     */
    @Override
    public void passApproachEvent(ApproachEvent approachEvent) {
        floorSubsystem.addEventToQueue(approachEvent);
    }

    /**
     * Receives an ApproachEvent from the Subsystem and returns it to the component.
     *
     * @param approachEvent the ApproachEvent to be received from the Subsystem
     */
    @Override
    public void receiveApproachEvent(ApproachEvent approachEvent) {
        arrivalSensor.compareToListOfRequests(approachEvent);
    }
}
