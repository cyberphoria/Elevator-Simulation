package requests;

import systemwide.Direction;

import java.time.LocalTime;

/**
 * ServiceRequest is a data structure for when a user requests anmElevator's service.
 *
 * @author Liam Tripp, Ramit Mahajan, Ryan Dash
 */
public class ServiceRequest extends SystemEvent {

	private final int floorNumber;
	private Direction direction;

	/**
	 * Constructor for ServiceRequest.
	 *
	 * @param time the time the request was made
	 * @param floorNumber the number of the floor on which the request was made
	 * @param direction the direction selected by the user
	 * @param origin the system from which the message originated
	 */
	public ServiceRequest(LocalTime time, int floorNumber, Direction direction, Thread origin) {
		super(time, origin);
		this.floorNumber = floorNumber;
		this.direction = direction;
	}

	/**
	 * Returns the number of the floor the request was made on.
	 *
	 * @return number of the floor the request was made on
	 */
	public int getFloorNumber() {
		return floorNumber;
	}

	/**
	 * Returns the direction indicated by the request.
	 *
	 * @return direction indicated by the request
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Sets the request's direction to the provided one.
	 *
	 * @param direction the direction to be changed for the request
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
