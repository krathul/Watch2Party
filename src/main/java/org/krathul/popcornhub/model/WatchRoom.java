package org.krathul.popcornhub.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class WatchRoom {

    @Id
    private String roomId;
    private String roomMaster;
    private ArrayList<String> roomAttendees;

    public WatchRoom() {
    }

    public WatchRoom(String roomId, String roomMaster) {
        this.roomId = roomId;
        this.roomMaster = roomMaster;
        this.roomAttendees = new ArrayList<String>();
    }

    public String getRoomID() {
        return this.roomId;
    }

    public String getRoomOwner() {
        return this.roomMaster;
    }

    public ArrayList<String> getRoomAttendees() {
        return this.roomAttendees;
    }

    public void addRoomAttendee(String user) {
        this.roomAttendees.add(user);
    }
}