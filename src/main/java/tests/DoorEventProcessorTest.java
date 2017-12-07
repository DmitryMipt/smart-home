package tests;

import org.junit.Test;
import ru.sbt.mipt.oop.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DoorEventProcessorTest {

    @Test
    public void testCloseDoor()  {

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

        for (Room room : rooms) {
            for (Door door : room.getDoors()) {
                assertTrue(door.isOpen());
            }
        }

        for (Room room : rooms) {
            smartHome.addRoom(room);
        }

        String closedDoorId = "4";

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, closedDoorId);
        DoorEventProcessor processor = new DoorEventProcessor();
        processor.reactOnEvent(event, smartHome);

        for (Room room : rooms) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(closedDoorId)) {
                    assertFalse(door.isOpen());
                } else {
                    assertTrue(door.isOpen());
                }
            }
        }
    }

    @Test
    public void testOpenDoor()  {

        SmartHome smartHome = new SmartHome();
        ArrayList<Light> lights = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lights.add(new Light(String.valueOf(i + 1), true));
        }

        ArrayList<Door> doors = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            doors.add(new Door(false, String.valueOf(i + 1)));
        }

        ArrayList<Room> rooms = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            rooms.add(new Room(lights, doors, "Room " + String.valueOf(i + 1)));
        }

        for (Room room : rooms) {
            for (Door door : room.getDoors()) {
                assertFalse(door.isOpen());
            }
        }

        for (Room room : rooms) {
            smartHome.addRoom(room);
        }

        String closedDoorId = "4";

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, closedDoorId);
        DoorEventProcessor processor = new DoorEventProcessor();
        processor.reactOnEvent(event, smartHome);

        for (Room room : rooms) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(closedDoorId)) {
                    assertTrue(door.isOpen());
                } else {
                    assertFalse(door.isOpen());
                }
            }
        }
    }


}

