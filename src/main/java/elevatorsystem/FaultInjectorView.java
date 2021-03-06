package elevatorsystem;

import javax.swing.*;
import java.util.ArrayList;

/**
 * FaultInjectorView is the GUI that helps to inject Door Stuck faults using a push button
 *
 * @author Julian
 */
public class FaultInjectorView {

    private final ArrayList<FaultButton> doorFaultButtons;
    private final ArrayList<FaultButton> cartFaultButtons;
    private final JPanel buttonListPanel;
    private final JPanel containerPanel;
    private final ArrayList<Elevator> elevatorList;

    /**
     * Class constructor with reference to elevator list.
     *
     * @ param elevatorList is the reference to all of the elevator from the elevator subsystem
     */
    public FaultInjectorView(ArrayList<Elevator> elevatorList) {
        this.elevatorList = elevatorList;
        int numberOfElevators = elevatorList.size();
        //Initializing the buttons
        this.doorFaultButtons = new ArrayList<>();
        this.cartFaultButtons = new ArrayList<>();
        buttonListPanel = new JPanel();
        for (int i = 0; i < numberOfElevators; i++) {
            doorFaultButtons.add(new FaultButton(elevatorList.get(i), Fault.DOOR_STUCK.getName()));
            cartFaultButtons.add(new FaultButton(elevatorList.get(i), Fault.ELEVATOR_STUCK.getName()));
            buttonListPanel.add(doorFaultButtons.get(i).getPanel());
            buttonListPanel.add(cartFaultButtons.get(i).getPanel());
        }

        JScrollPane scrollPane = new JScrollPane(buttonListPanel);
        scrollPane.setBorder(BorderFactory.createEtchedBorder());
        containerPanel = new JPanel();
        containerPanel.add(scrollPane);

        JFrame frame = new JFrame("Elevator Simulation");
        frame.add(containerPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
