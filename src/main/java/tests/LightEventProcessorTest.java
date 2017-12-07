package tests;

import org.junit.Test;
import ru.sbt.mipt.oop.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LightEventProcessorTest {

    @Test
    public void testLightTurnOn()  {
        SmartHome smartHome = new SmartHome();
        ArrayList<Light> lights = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lights.add(new Light(String.valueOf(i + 1), false));
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
            for (Light light : room.getLights()) {
                assertFalse(light.isOn());
            }
        }

        for (Room room : rooms) {
            smartHome.addRoom(room);
        }

        String lightId = "6";

        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, lightId);
       LightEventProcessor processor = new LightEventProcessor();
        processor.reactOnEvent(event, smartHome);

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(lightId)) {
                    assertTrue(light.isOn());
                } else {
                    assertFalse(light.isOn());
                }
            }
        }
    }

    @Test
    public void testLightTurnOff()  {
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
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }

        for (Room room : rooms) {
            smartHome.addRoom(room);
        }

        String lightId = "6";

        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, lightId);
        LightEventProcessor processor = new LightEventProcessor();
        processor.reactOnEvent(event, smartHome);

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(lightId)) {
                    assertFalse(light.isOn());
                } else {
                    assertTrue(light.isOn());
                }
            }
        }
    }

}