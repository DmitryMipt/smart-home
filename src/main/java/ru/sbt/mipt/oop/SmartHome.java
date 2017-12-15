package ru.sbt.mipt.oop;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Component{
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public void doIt(Action action) {
        for (Component room : rooms) {
            room.doIt(action);

        }
        action.doAction(this);


    }
}
