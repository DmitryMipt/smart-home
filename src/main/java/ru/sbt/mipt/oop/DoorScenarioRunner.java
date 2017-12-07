package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.Application.sendCommand;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorScenarioRunner implements EventProcessor {

    @Override
    public void reactOnEvent(SensorEvent event, SmartHome smartHome) {

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() != DOOR_OPEN) {
                        turnLightOffIfHallClosed(smartHome, room);
                    }
                }
            }
        }
    }


    private void turnLightOffIfHallClosed(SmartHome smartHome, Room room) {
        if (room.getName().equals("hall")) {
            for (Room homeRoom : smartHome.getRooms()) {
                for (Light light : homeRoom.getLights()) {
                    light.setOn(false);
                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                    sendCommand(command);
                }
            }
        }
    }
}
