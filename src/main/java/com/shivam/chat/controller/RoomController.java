package com.shivam.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.chat.entites.Message;
import com.shivam.chat.entites.Room;
import com.shivam.chat.repository.RoomRepository;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("http://localhost:3000")
public class RoomController {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@PostMapping
	public ResponseEntity<?> createRoom(@RequestBody String roomId) {
		
		if (roomRepository.findByRoomId(roomId)!= null) {
			
			return ResponseEntity.badRequest().body("Room is already exist");
		}
		
		Room room=new Room();
		room.setRoomId(roomId);
		Room saveRoom = roomRepository.save(room);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(room);
		
	}
	@GetMapping("/{roomId}")
	public ResponseEntity<?> joinRoom(@PathVariable String roomId){
		
		Room findByRoomId = roomRepository.findByRoomId(roomId);
		
		if (findByRoomId == null) {
			
			return ResponseEntity.badRequest().body("Room is not found!!");
			
		}
		
		return ResponseEntity.ok(findByRoomId);
		
	}
	
	@GetMapping("/{roomId}/message")
	public ResponseEntity<List<Message>> getAllMessage(@PathVariable String roomId,
			@RequestParam(value = "page",defaultValue = "0",required = false) int page,
			@RequestParam(value = "size",defaultValue = "20",required = false) int size){
		
	    Room room = roomRepository.findByRoomId(roomId);
	    
	    if (room == null) {
			return ResponseEntity.badRequest().build();
		}
		List<Message> messages = room.getMessages();
		int start=Math.max(0, messages.size() - (page +1) * size);
		int end=Math.min(messages.size(), start + size);
		List<Message> subList = messages.subList(start, end);
		return ResponseEntity.ok(subList);
	}

}

