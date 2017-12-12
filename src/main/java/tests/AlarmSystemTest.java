package tests;

import org.junit.Test;
import ru.sbt.mipt.oop.*;

import static org.junit.Assert.*;

public class AlarmSystemTest {
    @Test
    public void turnOnTest()  {
        //Из состояния OFF
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.setAlarmSystemState(new AlarmSystemOff(alarmSystem));
        alarmSystem.turnOn();
        assertEquals(alarmSystem.getState(), AlarmSystemEnum.ON);

        //Из состояния ON
        AlarmSystem alarmSystemStateON = new AlarmSystem();
        alarmSystemStateON.setAlarmSystemState(new AlarmSystemOn(alarmSystemStateON));
        alarmSystemStateON.turnOn();
        assertEquals(alarmSystemStateON.getState(), AlarmSystemEnum.ON);

        //Из состояния WAIT_FOR_PASSWORD
        AlarmSystem alarmSystemStateWaitForPassword = new AlarmSystem();
        alarmSystemStateWaitForPassword.setAlarmSystemState(new AlarmSystemWaitForPassword(alarmSystemStateWaitForPassword));
        alarmSystemStateWaitForPassword.turnOn();
        assertEquals(alarmSystemStateWaitForPassword.getState(), AlarmSystemEnum.WAIT_FOR_PASSWORD);


    }

    @Test
    public void turnOffTest() {
        //Из состояния OFF
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.setAlarmSystemState(new AlarmSystemOff(alarmSystem));
        alarmSystem.turnOff();
        assertEquals(alarmSystem.getState(), AlarmSystemEnum.OFF);

        //Из состояния ON
        AlarmSystem alarmSystemStateON = new AlarmSystem();
        alarmSystemStateON.setAlarmSystemState(new AlarmSystemOn(alarmSystemStateON));
        alarmSystemStateON.turnOff();
        assertEquals(alarmSystemStateON.getState(), AlarmSystemEnum.WAIT_FOR_PASSWORD);

        //Из состояния WAIT_FOR_PASSWORD
        AlarmSystem alarmSystemStateWaitForPassword = new AlarmSystem();
        alarmSystemStateWaitForPassword.setAlarmSystemState(new AlarmSystemWaitForPassword(alarmSystemStateWaitForPassword));
        alarmSystemStateWaitForPassword.turnOff();
        assertEquals(alarmSystemStateWaitForPassword.getState(), AlarmSystemEnum.WAIT_FOR_PASSWORD);
    }

    @Test
    public void onSensorEventTest() {
        //Из состояния OFF
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.setAlarmSystemState(new AlarmSystemOff(alarmSystem));
        alarmSystem.onSensorEvent(new SensorEvent(SensorEventType.DOOR_OPEN, "2345"));
        assertEquals(alarmSystem.getState(), AlarmSystemEnum.OFF);

        //Из состояния ON
        AlarmSystem alarmSystemStateON = new AlarmSystem();
        alarmSystemStateON.setAlarmSystemState(new AlarmSystemOn(alarmSystemStateON));
        alarmSystemStateON.onSensorEvent(new SensorEvent(SensorEventType.DOOR_OPEN, "1234"));
        assertEquals(alarmSystemStateON.getState(), AlarmSystemEnum.ALARM);

        //Из состояния WAIT_FOR_PASSWORD
        AlarmSystem alarmSystemStateWaitForPassword = new AlarmSystem();
        alarmSystemStateWaitForPassword.setAlarmSystemState(new AlarmSystemWaitForPassword(alarmSystemStateWaitForPassword));
        alarmSystemStateWaitForPassword.onSensorEvent(new SensorEvent(SensorEventType.DOOR_OPEN, "1234"));
        assertEquals(alarmSystemStateWaitForPassword.getState(), AlarmSystemEnum.WAIT_FOR_PASSWORD);

    }

    @Test
    public void enterPasswordTest()  {
        //Из состояния OFF
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.setAlarmSystemState(new AlarmSystemOff(alarmSystem));
        alarmSystem.enterPassword("admin");
        assertEquals(alarmSystem.getState(), AlarmSystemEnum.OFF);

        //Из состояния ON
        AlarmSystem alarmSystemStateON = new AlarmSystem();
        alarmSystemStateON.setAlarmSystemState(new AlarmSystemOn(alarmSystemStateON));
        alarmSystemStateON.turnOn();
        assertEquals(alarmSystemStateON.getState(), AlarmSystemEnum.ON);

        //Из состояния WAIT_FOR_PASSWORD (Correct password)
        AlarmSystem alarmSystemStateWaitForPasswordCorrect = new AlarmSystem();
        alarmSystemStateWaitForPasswordCorrect.setAlarmSystemState(new AlarmSystemWaitForPassword(alarmSystemStateWaitForPasswordCorrect));
        alarmSystemStateWaitForPasswordCorrect.enterPassword("admin");
        assertEquals(alarmSystemStateWaitForPasswordCorrect.getState(), AlarmSystemEnum.OFF);

        //Из состояния WAIT_FOR_PASSWORD (Incorrect password)
        AlarmSystem alarmSystemStateWaitForPasswordIncorrect = new AlarmSystem();
        alarmSystemStateWaitForPasswordIncorrect.setAlarmSystemState(new AlarmSystemWaitForPassword(alarmSystemStateWaitForPasswordIncorrect));
        alarmSystemStateWaitForPasswordIncorrect.enterPassword("admingfhj");
        assertEquals(alarmSystemStateWaitForPasswordIncorrect.getState(), AlarmSystemEnum.ALARM);



    }

}