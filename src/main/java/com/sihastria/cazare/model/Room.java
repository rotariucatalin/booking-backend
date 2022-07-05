package com.sihastria.cazare.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "room")
@Getter@Setter
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id")
	private long id;

	@Column(name = "name")
	private String roomName;

	@Column(name = "total_beds")
	private int totalBeds;

	@Column(name = "in_use")
	private int inUse;




	public Room() {

	}

	public Room(String roomName , int totalBeds) {
		this.roomName = roomName;
		this.totalBeds = totalBeds;
	}



}
