package com.shivam.chat.entites;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection  = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
	
	private String id;
	
	private String roomId;
	
	private List<Message> messages=new ArrayList<>();

}
