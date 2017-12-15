package tests;

import org.junit.Test;
import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class TestCompositor {
    @Test
    public void testComponentComposite() {
        List<Light> lights1 = Arrays.asList(new Light("1", false), new Light("2", true));
        List<Door> doors1 = Arrays.asList(new Door(false, "1"));
        Room kitchen = new Room(lights1, doors1,"kitchen");

        List<Light> lights2 = Arrays.asList(new Light("3", true));
        List<Door> doors2 = Arrays.asList(new Door(false, "2"));
        Room bathroom = new Room(lights2, doors2,"bathroom");

        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom));

        Set<Object> unvisitedObjects = new HashSet<>();
        unvisitedObjects.add(smartHome);
        unvisitedObjects.add(kitchen);
        unvisitedObjects.add(bathroom);
        unvisitedObjects.addAll(lights1);
        unvisitedObjects.addAll(lights2);
        unvisitedObjects.addAll(doors1);
        unvisitedObjects.addAll(doors2);

        smartHome.doIt(object -> {
            unvisitedObjects.remove(object);
        });
        assertEquals(0, unvisitedObjects.size());
    }
}