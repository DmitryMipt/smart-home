package ru.sbt.mipt.oop;

import java.util.Objects;

public class AlarmSystemAlarm implements AlarmSystemState {

    private AlarmSystem alarmSystem;

    public AlarmSystemAlarm(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }



    @Override
    public AlarmSystemEnum getState() {
        return AlarmSystemEnum.ALARM;
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
        }

    }
}
