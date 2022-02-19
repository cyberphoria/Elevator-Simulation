package requests;

public class StatusRequest extends SystemEvent {

    private final FloorRequest floorRequest;
    private final int elevatorFloor;

    public StatusRequest(FloorRequest floorRequest, Thread origin, int elevatorFloor) {
        super(floorRequest.getTime(), origin);
        this.floorRequest = floorRequest;
        this.elevatorFloor = elevatorFloor;
    }

    public FloorRequest getFloorRequest() {
        return floorRequest;
    }

    public int getElevatorNumber() {
        return elevatorFloor;
    }
}
