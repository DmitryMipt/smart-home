package ru.sbt.mipt.oop;

public class AlarmSystem implements AlarmSystemState{
    private AlarmSystemState alarmSystemState;
    private final String password = "admin";

    public String getPassword() {
        return password;
    }

    public void setAlarmSystemState(AlarmSystemState alarmSystemState) {
        this.alarmSystemState = alarmSystemState;
    }


    @Override
    public AlarmSystemEnum getState() {
        return alarmSystemState.getState();
    }

    @Override
    public void turnOn() {
        alarmSystemState.turnOn();

    }

    @Override
    public void turnOff() {
        alarmSystemState.turnOff();

    }

    @Override
    public void onSensorEvent(SensorEvent sensorEvent) {
        alarmSystemState.onSensorEvent(sensorEvent);

    }

    @Override
    public void enterPassword(String password) {
        alarmSystemState.enterPassword(password);

    }
}
