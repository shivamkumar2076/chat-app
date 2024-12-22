package com.shivam.chat.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import com.shivam.chat.entites.Message;
import com.shivam.chat.entites.Room;
import com.shivam.chat.payload.MessageRequest;
import com.shivam.chat.repository.RoomRepository;

@Controller
@CrossOrigin("http://localhost:3000")
public class ChatController {
	
	
	@Autowired
	private RoomRepository roomRepository;
	
	
	
	@MessageMapping("/sendMessage/{roomId}")
	@SendTo("/topic/room/{roomId}")
	public Message sendMessage(
			@DestinationVariable String roomId,
			@RequestBody MessageRequest request
			) {
		
		Room room = roomRepository.findByRoomId(request.getRoomId());
		
		Message message=new Message();
		message.setContent(request.getContent());
		message.setSender(request.getSender());
		message.setTimeStamp(LocalDateTime.now());
		
		if (room != null) {
			room.getMessages().add(message);
			roomRepository.save(room);
			
		}else {
			
			throw new RuntimeException("Room id not found");
		}
		
		return message;
	}

}
