package ru.sbt.mipt.oop;

public interface AlarmSystemState {
    AlarmSystemEnum getState();

    void turnOn();

    void turnOff();

    void onSensorEvent(SensorEvent sensorEvent);

    void enterPassword(String password);
}
