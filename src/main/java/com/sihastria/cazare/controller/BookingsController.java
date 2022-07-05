package com.sihastria.cazare.controller;

import com.sihastria.cazare.model.Guest;
import com.sihastria.cazare.model.Room;
import com.sihastria.cazare.model.dto.ReservationDto;
import com.sihastria.cazare.model.dto.ReservationPreviewDto;
import com.sihastria.cazare.repository.RoomRepository;
import com.sihastria.cazare.service.BookingService;
import com.sihastria.cazare.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/v1/api")
public class BookingsController {

    private final BookingService bookingService;
	private final RoomRepository roomRepository;
	private final RoomService roomService;

	public BookingsController(BookingService bookingService, RoomRepository roomRepository, RoomService roomService) {
		this.bookingService = bookingService;
		this.roomRepository = roomRepository;
		this.roomService = roomService;
	}


	@PostMapping("/booking")
	public ResponseEntity<Guest> createReservation(@RequestBody ReservationDto reservationDto) {
		Guest guestReservation = roomService.createReservation(reservationDto);
		return new ResponseEntity<>(guestReservation, HttpStatus.CREATED);
	}


	@GetMapping("/check-reservation-availability")
	public ResponseEntity<?> checkReservationAvailability(@RequestParam long roomId,
														  @RequestParam LocalDate startDate,
														  @RequestParam LocalDate endDate) {
		int noRoomsAvailable = bookingService.getNumberOfRoomsAvailableOnReservation(roomId, startDate, endDate);
		return new ResponseEntity<>(noRoomsAvailable, HttpStatus.OK);
	}


	@GetMapping("/booking")
	public ResponseEntity<List<ReservationPreviewDto>> getAllReservations(@RequestParam(required = true) LocalDate startDate,
																		  @RequestParam(required = true) LocalDate endDate) {
		List<ReservationPreviewDto> reservationPreviewDtos = bookingService.getAllReservationByDateRange(startDate, endDate);
			return new ResponseEntity<>(reservationPreviewDtos, HttpStatus.OK);
	}


	@GetMapping("/booking/{bookingId}")
	public ResponseEntity<ReservationDto> getReservationById(@PathVariable("bookingId") long bookingId) {
		ReservationDto reservationPreviewDtos = bookingService.getReservationById(bookingId);
		return new ResponseEntity<>(reservationPreviewDtos, HttpStatus.OK);
	}

	@PutMapping("/booking-delete/{bookingId}")
	public ResponseEntity<Room> softDeleteReservation(@PathVariable("bookingId") long bookingId) {
		bookingService.softDelete(bookingId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/booking-update/{id}")
	public ResponseEntity<Guest> updateReservation(@PathVariable("id") long id, @RequestBody ReservationDto reservationDto) {
		Guest guestReservation = roomService.updateReservation(id,reservationDto);
		return new ResponseEntity<>(guestReservation, HttpStatus.OK);
	}

}
