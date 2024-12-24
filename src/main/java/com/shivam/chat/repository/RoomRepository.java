package com.shivam.chat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shivam.chat.entites.Room;

@Repository
public interface RoomRepository extends MongoRepository<Room, String>{
	
	Room findByRoomId(String roomId);

}
