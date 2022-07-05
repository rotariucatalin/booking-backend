package com.sihastria.cazare.repository;

import com.sihastria.cazare.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
	List<Room> findByRoomName(String name);

	List<Room> findByInUse(int inUse);

	Room findByIdAndInUse(long id, int inUse);

}
