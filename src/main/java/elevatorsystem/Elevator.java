package elevatorsystem;

import requests.ElevatorRequest;
import systemwide.Direction;

/**
 * Requirements:
 * 1. Can move between floors
 * 2. Only services one elevator shaft of a structure
 * 3. Has speed
 * 4. Can stop at floors
 * 5. Knows it's own location
 * 6. Takes time for elevator to move
 * 7. travels at SPEED to traverse FLOOR HEIGHT per second
 *
 * @author Liam Tripp, Brady Norton
 */
public class Elevator {

	// Elevator Measurements
	public static final float MAX_SPEED = 2.67f; // meters/second
	public static final float ACCELERATION = 0.304f; // meters/second^2
	public static final float LOAD_TIME = 9.5f; // seconds
	public static final float FLOOR_HEIGHT = 3.91f; // meters (22 steps/floor @ 0.1778 meters/step)
	public static final double ACCELERATION_DISTANCE = Math.pow(MAX_SPEED, 2)/ (2 * ACCELERATION); // Vf^2 = Vi^2 + 2as therefore s = vf^2/2a
	public static final double ACCELERATION_TIME = Math.sqrt((FLOOR_HEIGHT * 2) / ACCELERATION); //s = 1/2at^2 therefore t = sqrt(s*2/a)


	// Elevator Properties

	private ElevatorSubsystem elevatorSubsystem;
	private final int elevatorNumber;
	private int currentFloor;
	private float speed, displacement;
	private final ElevatorMotor motor;
	private Direction currentDirection;
	private double queueTime;
	private FloorsQueue floorsQueue;

	/**
	 * Constructor for Elevator class
	 * Instantiates subsystem, currentFloor, speed, displacement, and status
	 *
	 * @param elevatorNumber
	 * @param elevatorSubsystem
	 */
	public Elevator(int elevatorNumber, ElevatorSubsystem elevatorSubsystem) {
		this.elevatorSubsystem = elevatorSubsystem;
		this.elevatorNumber = elevatorNumber;
		speed = 0;
		displacement = 0;
		currentDirection = Direction.STOP;
		motor = new ElevatorMotor();
		queueTime = 0.0;
		floorsQueue = new FloorsQueue();
	}

	/**
	 * Calculates the amount of time it will take for the elevator to stop at it's current speed
	 *
	 * @return total time it will take to stop as a float
	 */
	public float stopTime() {
		float numerator = 0 - speed;
		return numerator / ACCELERATION;
	}

	/**
	 * Gets the distance until the next floor as a float
	 *
	 * @return distance until next floor
	 */
	public float getDistanceUntilNextFloor(){
		float distance = 0;
		float stopTime = stopTime();

		// Using Kinematics equation: d = vt + (1/2)at^2
		float part1 = speed*stopTime;
		System.out.println("Part 1: " + part1);

		float part2 = (float) ((0.5)*(ACCELERATION)*(Math.pow(stopTime,2)));
		System.out.println("Part 2: " + part2);

		return part1 - part2;
	}

	/**
	 * Checks if the elevator is currently active (in motion)
	 *
	 * @return true if elevator is moving
	 */
	public MovementState getState() {
		return motor.getMovementState();
	}

	/**
	 * Gets the current floor the elevator is on
	 *
	 * @return the current floor as an int
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * Sets the currentFloor that the elevator is on
	 *
	 * @param currentFloor the floor to set the elevator on
	 */
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	/**
	 * Gets the Direction the elevator is heading
	 *
	 * @return Direction
	 */
	public Direction getCurrentDirection() {
		return currentDirection;
	}

	/**
	 * Sets the direction of the elevator
	 *
	 * @param currentDirection the elevator will be moving
	 */
	public void setCurrentDirection(Direction currentDirection) {
		this.currentDirection = currentDirection;
	}

	/**
	 * Gets the speed of the elevator
	 *
	 * @return the speed as a float
	 */
	public float getSpeed(){
		return speed;
	}

	/**
	 * Sets the speed of the elevator
	 * @param speed
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * Gets the displacement that the elevator has moved on the current floor
	 *
	 * @return displacement of elevator as float
	 */
	public float getFloorDisplacement() {
		return displacement;
	}

	/**
	 * Sets the displacement the elevator for the current floor
	 *
	 * @param displacement the displacement of the elevator as a float
	 */
	public void setFloorDisplacement(float displacement) {
		this.displacement = displacement;
	}
  
  /**
     * Adds the expected time it will take for the elevator to perform the
	 * elevator request to the queueTime and adds a request to the queue.
     *
     * @param elevatorRequest an elevator request from the floorSubsystem
     */
    public void addRequest(ElevatorRequest elevatorRequest) {
		queueTime = getExpectedTime(elevatorRequest);
        if (floorsQueue.isEmpty() == 0){
            currentDirection = elevatorRequest.getDirection();
        }
		System.out.print("Elevator# " + elevatorNumber + " ");
		int desiredFloor =  elevatorRequest.getDesiredFloor();
		Direction requestDirection = elevatorRequest.getDirection();

		if (desiredFloor >= currentFloor && desiredFloor < floorsQueue.peekNextFloor(currentDirection)){
			floorsQueue.addFloor(elevatorRequest.getDesiredFloor(), requestDirection);
			// Add request to the up queue
		} else if (desiredFloor <= currentFloor && desiredFloor > floorsQueue.peekNextFloor(currentDirection)){
			floorsQueue.addFloor(elevatorRequest.getDesiredFloor(), requestDirection);
			// Add request to the down queue
		} else {
			floorsQueue.addFloor(elevatorRequest.getDesiredFloor(), requestDirection);
			// Add to third queue
		}
        motor.setMovementState(MovementState.ACTIVE);
    }

	/**
	 * Gets the total expected time that the elevator will need to take to
	 * perform its current requests along with the new elevatorRequest.
	 *
	 * @param elevatorRequest an elevator request from the floorSubsystem
	 * @return a double containing the elevator's total expected queue time
	 */
	public double getExpectedTime(ElevatorRequest elevatorRequest) {
		return queueTime + LOAD_TIME + requestTime(elevatorRequest);
	}

	/**
	 * Gets the expected time of a new request for the current elevator
	 * based on distance.
	 *
	 * @param elevatorRequest an elevatorRequest from a floor
	 * @return a double containing the time to fulfil the request
	 */
	public double requestTime(ElevatorRequest elevatorRequest) {
		double distance = Math.abs(elevatorRequest.getDesiredFloor() - currentFloor) * FLOOR_HEIGHT;
		if (distance > ACCELERATION_DISTANCE * 2) {
			return (distance - ACCELERATION_DISTANCE * 2) / MAX_SPEED + ACCELERATION_TIME * 2;
		} else {
			return Math.sqrt(distance * 2 / ACCELERATION); // elevator accelerates and decelerates continuously
		}
	}

	public int getElevatorNumber() {
		return elevatorNumber;
	}
}
