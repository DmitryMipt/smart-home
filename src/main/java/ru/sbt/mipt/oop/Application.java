package ru.sbt.mipt.oop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

import static ru.sbt.mipt.oop.EventCreator.getNextSensorEvent;
import static ru.sbt.mipt.oop.SmartHomeFileReader.readSmartHome;

public class Application {

    public static void main(String... args) throws IOException {

       // ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");

        SmartHome smartHome = readSmartHome();
        SensorEvent event = getNextSensorEvent();
        EventObserver eventProcessor =  new EventObserver(smartHome);//(EventObserver) ctx.getBean("EventObserver");
        eventProcessor.editEvent(event, smartHome);
    }

    public static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }


}
