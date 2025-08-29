package org.krathul.popcornhub.controller;

import org.krathul.popcornhub.model.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WatchRoomController {
    private SimpMessagingTemplate messageTemplate;

    @MessageMapping("/chat/{roomId}")
    public void sendMessage(@DestinationVariable String roomId, @Payload Message message) {
        messageTemplate.convertAndSend("topic/chat/" + roomId, message.payload);
    }

    @SubscribeMapping("/join/{roomId}")
    public void joinChat(@DestinationVariable String roomId, @Payload Message message) {
        messageTemplate.convertAndSend("topic/chat/" + roomId, message.sender + "has joined the chat!");
    }

    @MessageMapping("/sync/{roomId}")
    public void sync(@DestinationVariable String roomId, @Payload Message message) {
        messageTemplate.convertAndSend("topic/sync" + roomId, message);
    }
}
