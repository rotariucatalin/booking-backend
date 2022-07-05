package com.sihastria.cazare.repository;

import com.sihastria.cazare.model.Booking;

import com.sihastria.cazare.model.dto.AvailabilityDto;
import com.sihastria.cazare.model.dto.ReservationDto;
import com.sihastria.cazare.model.dto.ReservationPreviewDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(nativeQuery=true)
    List<AvailabilityDto> findAvailableRoom(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("roomId") long roomId);

    @Query(nativeQuery=true)
    List<ReservationPreviewDto> findAllReservations(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(nativeQuery=true)
    ReservationDto findReservationById(@Param("bookingId") long bookingId);


}
