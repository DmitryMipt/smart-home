package tests;

import org.junit.Test;
import ru.sbt.mipt.oop.*;

import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EventObserverTest {
    @Test
    public void editEventTest()  {

        //Создаем SmartHome и events
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
            smartHome.addRoom(room);
        }

        String closedDoorId = "4";
        String lightId = "6";

        //event - открытие двери номер 6
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, closedDoorId);
        DoorEventProcessor processor = new DoorEventProcessor();
        processor.reactOnEvent(event, smartHome);

        //event выключение света номер 4
        SensorEvent event2 = new SensorEvent(SensorEventType.LIGHT_OFF, lightId);
        LightEventProcessor processor2 = new LightEventProcessor();
        processor2.reactOnEvent(event, smartHome);


        //СОЗДАЕМ точно такой же SmartHome как и был до events
        SmartHome smartHome2 = new SmartHome();
        ArrayList<Light> lights2 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lights2.add(new Light(String.valueOf(i + 1), true));
        }

        ArrayList<Door> doors2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            doors2.add(new Door(false, String.valueOf(i + 1)));
        }

        ArrayList<Room> rooms2 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            rooms2.add(new Room(lights, doors, "Room " + String.valueOf(i + 1)));
        }


        for (Room room : rooms2) {
            smartHome2.addRoom(room);
        }

                //Создаем такие же события, но не реагируем на них
        SensorEvent event3 = new SensorEvent(SensorEventType.DOOR_OPEN, closedDoorId);
        DoorEventProcessor processor3 = new DoorEventProcessor();

        SensorEvent event4 = new SensorEvent(SensorEventType.LIGHT_OFF, lightId);
        LightEventProcessor processor4 = new LightEventProcessor();

        List<EventProcessor> eventProcessors = new ArrayList<>();
        eventProcessors.add(processor3);
        eventProcessors.add(processor4);

        //Создаем объект EventHandler, передаем ему эти события и выполняем метод editEvent()
        EventObserver eventObserver = new EventObserver();
        eventObserver.setEventProcessors(eventProcessors);
        eventObserver.editEvent(smartHome2);

        //ТЕПЕРЬ проверяем, что в результате двух разных подходов по обработке событий - для двух одинаковых домов
        // получается один и тот же результат

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(lightId)) {
                    for (Room room2 : rooms) {
                        for (Light light2 : room.getLights()) {
                            if (light2.getId().equals(lightId)) {
                                assertEquals(light.isOn(), light2.isOn());
                            }
                        }

                    }
                }
            }
        }
        for (Room room : rooms) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(closedDoorId)) {
                    for (Room room2 : rooms) {
                        for (Door door2 : room.getDoors()) {
                            if (door2.getId().equals(closedDoorId)) {
                                assertEquals(door.isOpen(), door2.isOpen());
                            }
                        }
                    }


                }
            }
        }
    }

}

