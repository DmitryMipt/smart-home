package ru.sbt.mipt.oop;

public class AlarmSystemOff implements AlarmSystemState {

    private AlarmSystem alarmSystem;

    public AlarmSystemOff(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }



    @Override
    public AlarmSystemEnum getState() {
        return AlarmSystemEnum.OFF ;
    }

    @Override
    public void turnOn() {
        alarmSystem.setAlarmSystemState(new AlarmSystemOn(alarmSystem));

    }

    @Override
    public void turnOff() {

    }

    @Override
    public void onSensorEvent(SensorEvent sensorEvent) {

    }

    @Override
    public void enterPassword(String password) {

    }
}
