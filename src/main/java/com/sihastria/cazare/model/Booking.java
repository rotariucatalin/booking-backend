package com.sihastria.cazare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sihastria.cazare.model.dto.AvailabilityDto;
import com.sihastria.cazare.model.dto.ReservationDto;
import com.sihastria.cazare.model.dto.ReservationPreviewDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "booking")
@Getter@Setter

@NamedNativeQuery(name = "Booking.findAvailableRoom",
		query = "SELECT bk.booking_id as bookingId, bk.start_date as startDate, bk.end_date as endDate, " +
				"bk.required_bed as requiredBed, rm.total_beds as totalBeds FROM booking bk\n" +
				"join room rm on bk.room_id = rm.room_id\n" +
				"join guest gs on bk.`guest_id` = gs.guest_id\n" +
				"WHERE\n" +
				"(bk.start_date BETWEEN :startDate AND :endDate OR\n" +
				":startDate BETWEEN bk.start_date AND bk.end_date)\n" +
				"AND bk.room_id=:roomId AND bk.soft_delete=0",
		resultSetMapping = "Booking.findAvailableRoom")
@SqlResultSetMapping(name = "Booking.findAvailableRoom",
		classes = @ConstructorResult(targetClass = AvailabilityDto.class,
				columns = {@ColumnResult(name = "bookingId", type = long.class),
						@ColumnResult(name = "startDate", type = LocalDate.class),
						@ColumnResult(name = "endDate", type = LocalDate.class),
						@ColumnResult(name = "requiredBed", type = Integer.class),
						@ColumnResult(name = "totalBeds", type = Integer.class)}))

@NamedNativeQuery(name = "Booking.findAllReservations",
		query = "SELECT bk.booking_id as id, \n" +
				"rm.room_id AS room_id, \n" +
				"gs.name as name, \n" +
				"gs.additional_details as additionalInformation, \n" +
				"bk.required_bed as noBeds, \n" +
				"gs.phone_no as phone, \n" +
				"gs.city as county, \n" +
				"bk.start_date as startDate, \n" +
				"bk.end_date as endDate \n" +
				"FROM booking bk \n" +
				"join room rm on bk.room_id = rm.room_id \n" +
				"join guest gs on bk.`guest_id` = gs.guest_id \n" +
				"WHERE \n" +
				"(bk.start_date BETWEEN :startDate AND :endDate OR \n" +
				":startDate BETWEEN bk.start_date AND bk.end_date) AND bk.soft_delete=0 \n",
		resultSetMapping = "Booking.findAllReservations")
@SqlResultSetMapping(name = "Booking.findAllReservations",
		classes = @ConstructorResult(targetClass = ReservationPreviewDto.class,
				columns = {@ColumnResult(name = "id", type = long.class),
						@ColumnResult(name = "room_id", type = long.class),
						@ColumnResult(name = "name", type = String.class),
						@ColumnResult(name = "additionalInformation", type = String.class),
						@ColumnResult(name = "noBeds", type = Integer.class),
						@ColumnResult(name = "phone", type = String.class),
						@ColumnResult(name = "county", type = String.class),
						@ColumnResult(name = "startDate", type = LocalDate.class),
						@ColumnResult(name = "endDate", type = LocalDate.class)

						}))

@NamedNativeQuery(name = "Booking.findReservationById",
		query = "SELECT bk.room_id as roomId, gs.name as name, gs.phone_no as phoneNumber, gs.city, \n" +
				"gs.additional_details as additionalDetails, bk.start_date as startDate, bk.end_date as endDate,\n" +
				"bk.required_bed as requiredBed\n" +
				"FROM booking bk\n" +
				"join room rm on bk.room_id = rm.room_id\n" +
				"join guest gs on bk.`guest_id` = gs.guest_id\n" +
				"WHERE bk.booking_id=:bookingId",
		resultSetMapping = "Booking.findReservationById")
@SqlResultSetMapping(name = "Booking.findReservationById",
		classes = @ConstructorResult(targetClass = ReservationDto.class,
				columns = {@ColumnResult(name = "roomId", type = long.class),
						@ColumnResult(name = "name", type = String.class),
						@ColumnResult(name = "phoneNumber", type = String.class),
						@ColumnResult(name = "city", type = String.class),
						@ColumnResult(name = "additionalDetails", type = String.class),
						@ColumnResult(name = "startDate", type = LocalDate.class),
						@ColumnResult(name = "endDate", type = LocalDate.class),
						@ColumnResult(name = "requiredBed", type = Integer.class)}))
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private long id;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "required_bed")
	private int requiredBed;

	@Column(name = "softDelete")
	private int softDelete;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="guest_id", nullable=false)
	private Guest guest;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="room_id", nullable=false)
	private Room room;


	public Booking() {

	}


	public Booking(LocalDate startDate, LocalDate endDate, int requiredBed, int softDelete, Guest guest, Room room) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.requiredBed = requiredBed;
		this.softDelete = softDelete;
		this.guest = guest;
		this.room = room;
	}

}
