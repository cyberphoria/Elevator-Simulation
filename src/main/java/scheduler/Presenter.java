package scheduler;

import requests.ElevatorMonitor;

/**
 * Presenter updates the View of the system with information from the Model.
 *
 * @author Liam Tripp
 */
public class Presenter {

    private ElevatorViewContainer elevatorViewContainer;

    /**
     * Constructor for Presenter.
     */
    public Presenter() {

    }

    /**
     * Adds an ElevatorViewContainer to the Presenter.
     *
     * @param elevatorViewContainer the View component that displays the list of elevators
     */
    public void addView(ElevatorViewContainer elevatorViewContainer) {
        this.elevatorViewContainer = elevatorViewContainer;
    }

    // TODO: decide between Scheduler and ElevatorSubsystem / ElevatorMessenge
    //  for the model
    /*
    public void addModel() {

    }
     */

    /**
     * Updates the ElevatorView corresponding to the ElevatorMonitor's elevator number.
     *
     * @param elevatorMonitor the elevatorMonitor containing the status information of the Elevator
     */
    public void updateElevatorView(ElevatorMonitor elevatorMonitor) {
        ElevatorView elevatorView = elevatorViewContainer.getElevatorView(elevatorMonitor.getElevatorNumber());
        elevatorView.update(elevatorMonitor);
    }
}
