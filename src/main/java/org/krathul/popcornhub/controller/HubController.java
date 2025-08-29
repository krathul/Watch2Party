package org.krathul.popcornhub.controller;

import java.util.ArrayList;

import org.krathul.popcornhub.model.WatchRoom;
import org.krathul.popcornhub.service.WatchRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class HubController {
    private WatchRoomService watchRoomService;

    public HubController(WatchRoomService watchRoomService) {
        this.watchRoomService = watchRoomService;
    }

    /**
     *
     * @param skeleton a WatchRoom object with object with a placeholder value
     *                 for roomId. eg. {"", Jane}
     * @return roomID for the created room
     */
    @PutMapping("/create")
    public ResponseEntity<String> createRoom(@RequestBody WatchRoom skeleton) {
        WatchRoom newWatchRoom = watchRoomService.createRoom(skeleton.getRoomOwner());
        return ResponseEntity.ok(newWatchRoom.getRoomID());
    }

    /**
     * 
     * @param roomId
     * @return roomAttendees of the room
     */
    @GetMapping("/get/{roomId}")
    public ResponseEntity<ArrayList<String>> getRoom(@PathVariable String roomId) {
        return watchRoomService.findRoom(roomId)
                .map(watchRoom -> ResponseEntity.ok(watchRoom.getRoomAttendees()))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 
     * @param roomId
     * @param user
     * @return boolean
     */
    @PostMapping("/join/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId, @RequestBody String user) {
        if (watchRoomService.addToRoom(roomId, user))
            return ResponseEntity.ok(null);

        return ResponseEntity.notFound().build();
    }
}
