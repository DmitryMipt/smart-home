package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.sbt.mipt.oop.EventCreator.getNextSensorEvent;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SmartHomeFileReader.readSmartHome;

public class Application {

    public static void main(String... args) throws IOException {

        SmartHome smartHome = readSmartHome();
        EventHandler eventProcessor = new EventHandler();
        eventProcessor.editEvent(smartHome);
    }

    public static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }


}
