package ru.sbt.mipt.oop;

import java.util.Objects;

public class AlarmSystemWaitForPassword implements AlarmSystemState {

    private AlarmSystem alarmSystem;

    public AlarmSystemWaitForPassword(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }



    @Override
    public AlarmSystemEnum getState() {
        return AlarmSystemEnum.WAIT_FOR_PASSWORD;
    }

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }

    @Override
    public void onSensorEvent(SensorEvent sensorEvent) {

    }

    @Override
    public void enterPassword(String password) {
        if (Objects.equals(alarmSystem.getPassword(), password)) {
            alarmSystem.setAlarmSystemState(new AlarmSystemOff(alarmSystem));
        } else {
            alarmSystem.setAlarmSystemState(new AlarmSystemAlarm(alarmSystem));
        }


    }
}
