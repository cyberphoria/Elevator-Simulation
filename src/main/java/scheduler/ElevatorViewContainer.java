package scheduler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * ElevatorViewContainer contains and displays a list of ElevatorViews. It provides
 * methods to access them.
 *
 * @author Liam Tripp
 */
public class ElevatorViewContainer {

    // TODO: some of these could easily be local variables
    private ArrayList<ElevatorView> elevatorViews;
    private JPanel elevatorListPanel;
    private JScrollPane scrollPane;
    private JPanel containerPanel;

    /**
     * Constructor for ElevatorViewContainer.
     *
     * @param numberOfElevators the number of ElevatorViews in the ElevatorViewList
     */
    public ElevatorViewContainer(int numberOfElevators) {
        this.elevatorViews = new ArrayList<>();
        elevatorListPanel = new JPanel(new GridLayout(numberOfElevators, 1));
        for (int i = 0; i < numberOfElevators; i++) {
            elevatorViews.add(new ElevatorView(i));
            elevatorListPanel.add(elevatorViews.get(i).getPanel());
        }

        JScrollPane scrollPane = new JScrollPane(elevatorListPanel);
        scrollPane.setBorder(BorderFactory.createEtchedBorder());
        containerPanel.add(scrollPane);
    }

    /**
     * Gets the list of ElevatorViews in the ElevatorViewContainer.
     *
     * @return the ElevatorViews contained in the ElevatorViewContainer's elevatorListPanel
     */
    public ArrayList<ElevatorView> getElevatorViews() {
        return elevatorViews;
    }

    /**
     * Returns an ElevatorView corresponding to an Elevator's Number.
     *
     * @param elevatorNumber the elevatorNumber of the Elevator
     * @return elevatorView corresponding to the ElevatorNumber.
     */
    public ElevatorView getElevatorView(int elevatorNumber) {
        if (elevatorNumber >= elevatorViews.size()) {
            String exceptionMessage = "The number " + elevatorNumber + " is greater " +
                    "than the actual number of ElevatorViews, " + elevatorViews.size() + ".";
            throw new IllegalArgumentException(exceptionMessage);
        }
        return elevatorViews.get(elevatorNumber);
    }
}
