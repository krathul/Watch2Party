package org.krathul.popcornhub.service;

import java.util.Optional;
import java.util.UUID;

import org.krathul.popcornhub.model.WatchRoom;
import org.krathul.popcornhub.repository.WatchRoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * WatchRoomService class that handles high level CRUD operations.
 */

@Service
public class WatchRoomService {
    @Autowired
    private WatchRoomRepository roomRepository;

    private String generateRoomId() {
        String IdPrefix = UUID.randomUUID().toString().substring(0, 6);
        return IdPrefix.toLowerCase();
    }

    public WatchRoomService(WatchRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Optional<WatchRoom> findRoom(String roomID) {
        return roomRepository.findById(roomID);
    }

    public WatchRoom createRoom(String roomMaster) {
        String roomId = generateRoomId();
        WatchRoom newWatchRoom = new WatchRoom(roomId, roomMaster);

        roomRepository.save(newWatchRoom);
        return newWatchRoom;
    }

    public boolean addToRoom(String roomId, String user) {
        Optional<WatchRoom> room = this.findRoom(roomId);

        if (room.isEmpty())
            return false;

        room.ifPresent(watchRoom -> {
            watchRoom.addRoomAttendee(user);
            roomRepository.save(watchRoom);
        });

        return true;
    }
}