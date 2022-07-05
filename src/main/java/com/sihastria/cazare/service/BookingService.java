package com.sihastria.cazare.service;


import com.sihastria.cazare.model.Booking;
import com.sihastria.cazare.model.Room;
import com.sihastria.cazare.model.dto.AvailabilityDto;
import com.sihastria.cazare.model.dto.ReservationDto;
import com.sihastria.cazare.model.dto.ReservationPreviewDto;
import com.sihastria.cazare.repository.BookingRepository;
import com.sihastria.cazare.repository.GuestRepository;
import com.sihastria.cazare.repository.RoomRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {


    private final GuestRepository guestRepository;
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final JdbcTemplate jdbcTemplate;


    public BookingService(GuestRepository guestRepository, BookingRepository bookingRepository,
                          RoomRepository roomRepository, JdbcTemplate jdbcTemplate) {
        this.guestRepository = guestRepository;
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Return number of rooms available for that room between a date range
     * @param roomId
     * @param startDate
     * @param endDate
     * @return
     */
    public int getNumberOfRoomsAvailableOnReservation(long roomId, LocalDate startDate, LocalDate endDate){
       List<AvailabilityDto> bookings = bookingRepository.findAvailableRoom(startDate.toString(), endDate.toString(), roomId);
       Room room = roomRepository.findByIdAndInUse(roomId, 1);
       if(bookings.isEmpty()){
        return room.getTotalBeds();
       }
       int totalNoBedsAvailable = bookings.stream().mapToInt(AvailabilityDto::getRequiredBed).sum();
       return room.getTotalBeds() - totalNoBedsAvailable;
    }


    /**
     * Get all reservations between a date range
     * @param startDate
     * @param endDate
     * @return
     */
    public List<ReservationPreviewDto> getAllReservationByDateRange(LocalDate startDate, LocalDate endDate){
        return bookingRepository.findAllReservations(startDate.toString(), endDate.toString());
    }


    /**
     * @param bookingId
     * @return
     */
    public ReservationDto getReservationById(long bookingId){
        return bookingRepository.findReservationById(bookingId);
    }

    /**
     * soft delete booking by id
     * @param bookingId
     */
    @Transactional
    public void softDelete(long bookingId){
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        booking.ifPresent(value -> value.setSoftDelete(1));
    }

}
