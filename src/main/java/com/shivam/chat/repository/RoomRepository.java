package com.shivam.chat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shivam.chat.entites.Room;

public interface RoomRepository extends MongoRepository<Room, String>{
	
	Room findByRoomId(String roomId);

}
