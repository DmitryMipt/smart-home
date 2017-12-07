package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.Application.sendCommand;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;


/*
*Класс выпо
 */
public class DoorEventProcessor implements EventProcessor {

    @Override
    public void reactOnEvent(SensorEvent event, SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    } else {
                        door.setOpen(false);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");

                    }
                }
            }
        }

    }
}




