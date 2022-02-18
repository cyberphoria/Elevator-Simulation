package elevatorsystem;

import requests.*;
import systemwide.BoundedBuffer;
import systemwide.Direction;

import java.util.ArrayList;


/**
 * ElevatorSubsystem manages the elevators and their requests to the Scheduler
 *
 * @author Liam Tripp, Julian, Ryan Dash
 */
public class ElevatorSubsystem implements Runnable, ServiceRequestListener {

	private final BoundedBuffer elevatorSubsystemBuffer; // Elevator Subsystem - Scheduler link
	private final ArrayList<Elevator> elevatorList;

	/**
	 * Constructor for ElevatorSubsystem.
	 *
	 * @param buffer the buffer the ElevatorSubsystem passes messages to and receives messages from
	 */
	public ElevatorSubsystem(BoundedBuffer buffer) {
		this.elevatorSubsystemBuffer = buffer;
		elevatorList = new ArrayList<>();
	}

	/**
	 * Adds an elevator to the subsystem's list of elevators.
	 *
	 * @param elevator an elevator
	 */
	public void addElevator(Elevator elevator) {
		elevatorList.add(elevator);
	}

	/**
	 * Simple message requesting and sending between subsystems.
	 *
	 */
	public void run() {
		while(true) {
			SystemEvent request = receiveMessage(elevatorSubsystemBuffer, Thread.currentThread());
			if (request instanceof ElevatorRequest elevatorRequest) {
				sendMessage(new FloorRequest(elevatorRequest, 1), elevatorSubsystemBuffer, Thread.currentThread());
				System.out.println(Thread.currentThread().getName() + " Sent Request Successful to Scheduler");
			}
		}
	}
}
