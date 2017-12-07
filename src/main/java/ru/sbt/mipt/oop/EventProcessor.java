package ru.sbt.mipt.oop;

public interface EventProcessor {
    void reactOnEvent(SensorEvent event, SmartHome smartHome);
}
