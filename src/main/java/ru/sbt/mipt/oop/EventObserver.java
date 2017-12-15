package ru.sbt.mipt.oop;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.sbt.mipt.oop.EventCreator.getNextSensorEvent;
import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

/*
* Класс с методом, обрабатывающим события. В качестве параметра передается нынешнее состояние дома.
 */

public class EventObserver {
    private List<EventProcessor> eventProcessors = new ArrayList<>();

    public EventObserver(SmartHome smartHome) {
    }

    public void setEventProcessors(List<EventProcessor> eventProcessors) {
        this.eventProcessors = eventProcessors;
    }

    public void editEvent(SensorEvent event, SmartHome smartHome) {


        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventProcessor processor : eventProcessors) {
                processor.reactOnEvent(event,smartHome);
            }
            event = getNextSensorEvent();
        }
    }


}
