package ru.sbt.mipt.oop;

import java.io.IOException;

import static ru.sbt.mipt.oop.SmartHomeFileReader.readSmartHome;

public class Application {

    public static void main(String... args) throws IOException {

        SmartHome smartHome = readSmartHome();
        EventObserver eventProcessor = new EventObserver();
        eventProcessor.editEvent(smartHome);
    }

    public static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }


}
