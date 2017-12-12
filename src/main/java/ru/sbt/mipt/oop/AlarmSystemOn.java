package ru.sbt.mipt.oop;

public class AlarmSystemOn implements AlarmSystemState {

    private AlarmSystem alarmSystem;

    public AlarmSystemOn(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }



    @Override
    public AlarmSystemEnum getState() {
        return AlarmSystemEnum.ON;
    }

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {
        alarmSystem.setAlarmSystemState(new AlarmSystemWaitForPassword(alarmSystem));
    }

    @Override
    public void onSensorEvent(SensorEvent sensorEvent) {
        alarmSystem.setAlarmSystemState(new AlarmSystemAlarm(alarmSystem));

    }

    @Override
    public void enterPassword(String password) {

    }
}
