package com.sihastria.cazare.controller;

import com.sihastria.cazare.model.Room;
import com.sihastria.cazare.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/v1/api")
public class RoomController {


	private final RoomRepository roomRepository;

	public RoomController(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	@GetMapping("/room")
	public ResponseEntity<List<Room>> getAllRooms() {
			List<Room> rooms = roomRepository.findByInUse(1);
			return new ResponseEntity<>(rooms, HttpStatus.OK);
	}


	@PostMapping("/room")
	public ResponseEntity<Room> createRoom(@RequestBody Room room) {
			Room room1 = roomRepository
					.save(new Room(room.getRoomName(), room.getTotalBeds()));
			return new ResponseEntity<>(room1, HttpStatus.CREATED);
	}

	@PutMapping("/room")
	public ResponseEntity<Room> updateRoom(@RequestBody Room room) {
		Optional<Room> roomData = roomRepository.findById(room.getId());
		if (roomData.isPresent()) {
			Room roomToUpdate = roomData.get();
			roomToUpdate.setRoomName(room.getRoomName());
			roomToUpdate.setTotalBeds(room.getTotalBeds());
			return new ResponseEntity<>(roomRepository.save(roomToUpdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/room-soft-delete/{id}")
	public ResponseEntity<Room> softDeleteRoom(@PathVariable("id") long id) {
		Optional<Room> roomData = roomRepository.findById(id);
		if (roomData.isPresent()) {
			Room roomToUpdate = roomData.get();
			roomToUpdate.setInUse(0);
			return new ResponseEntity<>(roomRepository.save(roomToUpdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


}
