package tests;

import org.junit.Test;
import ru.sbt.mipt.oop.*;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class DoorScenarioRunnerTest {

    @Test
    public void testTurnOffLightsWhenHallDoorClosed() {

        SmartHome smartHome = new SmartHome();
        ArrayList<Light> lights = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lights.add(new Light(String.valueOf(i + 1), true));
        }

        ArrayList<Door> doors = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            doors.add(new Door(true, String.valueOf(i + 1)));
        }

        ArrayList<Room> rooms = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            rooms.add(new Room(lights, doors, "Room " + String.valueOf(i + 1)));
        }
        String hallDoorId = "123456";
        rooms.add(new Room(lights, Collections.singletonList(new Door(true, hallDoorId)), "hall"));

        for (Room room : rooms) {
            smartHome.addRoom(room);
        }

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, hallDoorId);

        DoorScenarioRunner doorScenarioRunner = new DoorScenarioRunner();
        doorScenarioRunner.reactOnEvent(event,smartHome);

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertFalse(light.isOn());
            }
        }
    }

    @Test
    public void testCloseNotHallDoor() {
        SmartHome smartHome = new SmartHome();
        ArrayList<Light> lights = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lights.add(new Light(String.valueOf(i + 1), true));
        }

        ArrayList<Door> doors = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            doors.add(new Door(true, String.valueOf(i + 1)));
        }

        ArrayList<Room> rooms = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            rooms.add(new Room(lights, doors, "Room " + String.valueOf(i + 1)));
        }
        String hallDoorId = "123456";
        rooms.add(new Room(lights, Collections.singletonList(new Door(true, hallDoorId)), "hall"));

        for (Room room : rooms) {
            smartHome.addRoom(room);
        }

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "3");

        DoorScenarioRunner doorScenarioRunner = new DoorScenarioRunner();
        doorScenarioRunner.reactOnEvent(event,smartHome);

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }

        event = new SensorEvent(SensorEventType.DOOR_CLOSED, "Room 4");
        doorScenarioRunner.reactOnEvent(event,smartHome);

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }

        event = new SensorEvent(SensorEventType.DOOR_CLOSED, "Room 6");
        doorScenarioRunner.reactOnEvent(event,smartHome);

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }
    }
}