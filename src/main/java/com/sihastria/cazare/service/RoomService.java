package com.sihastria.cazare.service;

import com.sihastria.cazare.model.Booking;
import com.sihastria.cazare.model.Guest;
import com.sihastria.cazare.model.Room;
import com.sihastria.cazare.model.dto.ReservationDto;
import com.sihastria.cazare.repository.BookingRepository;
import com.sihastria.cazare.repository.GuestRepository;
import com.sihastria.cazare.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@Service
public class RoomService {


    private final GuestRepository guestRepository;
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public RoomService(GuestRepository guestRepository, BookingRepository bookingRepository,
                          RoomRepository roomRepository) {
        this.guestRepository = guestRepository;
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    public Guest createReservation(ReservationDto reservationDto) {
        Room room = roomRepository.getOne(reservationDto.getRoomId());
        Guest guest = new Guest(reservationDto.getName(), reservationDto.getPhoneNumber(), reservationDto.getCity(), reservationDto.getAdditionalDetails());
        Booking bookingDetails = new Booking(reservationDto.getStartDate(), reservationDto.getEndDate(), reservationDto.getRequiredBed(), 0, guest, room);
        guest.setBookings(Collections.singletonList(bookingDetails));
        return guestRepository.save(guest);
    }

    @Transactional
    public Guest updateReservation(long bookingId, ReservationDto reservationDto) {

        roomRepository.findById(reservationDto.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));
        Guest guest = new Guest(reservationDto.getName(), reservationDto.getPhoneNumber(), reservationDto.getCity(), reservationDto.getAdditionalDetails());
        Booking bookingDetails = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));

        bookingDetails.setGuest(guest);
        bookingDetails.setStartDate(reservationDto.getStartDate());
        bookingDetails.setEndDate(reservationDto.getEndDate());
        bookingDetails.setRequiredBed(reservationDto.getRequiredBed());

        guest.setBookings(Collections.singletonList(bookingDetails));

        return guestRepository.save(guest);
    }
}
