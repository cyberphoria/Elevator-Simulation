package floorsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.ServiceRequest;
import systemwide.Direction;
import systemwide.Origin;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ArrivalSensorTest {

    ArrivalSensor arrivalSensor;
    ServiceRequest serviceRequest;

    @BeforeEach
    void setUp() {
        // Create ArrivalSensor
        arrivalSensor = new ArrivalSensor(2);

        // Create ServiceRequest
        serviceRequest = new ServiceRequest(LocalTime.now(), 2, Direction.UP, Origin.SCHEDULER);
    }

    @Test
    void addRequest() {
        // Check requests in ArrivalSensor
        System.out.println("Size: " + arrivalSensor.requestsOnFloor.size());
        assertEquals(0, arrivalSensor.getRequestsOnFloorSize());

        // Add a request
        arrivalSensor.addRequest(serviceRequest);

        // Check ArrivalSensor requests again
        System.out.println("Size: " + arrivalSensor.requestsOnFloor.size());
        assertEquals(1, arrivalSensor.getRequestsOnFloorSize());
    }

    @Test
    void removeRequest() {
        // Check requests in ArrivalSensor
        System.out.println("Size: " + arrivalSensor.requestsOnFloor.size());
        assertEquals(0, arrivalSensor.getRequestsOnFloorSize());

        // Add a request
        arrivalSensor.addRequest(serviceRequest);

        // Check requests again
        assertEquals(1, arrivalSensor.getRequestsOnFloorSize());

        // Remove request
        arrivalSensor.removeRequest(serviceRequest);

        // Check requests again
        assertEquals(0, arrivalSensor.getRequestsOnFloorSize());
    }
}