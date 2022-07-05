package com.sihastria.cazare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "guest")
@Getter@Setter
public class Guest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "guest_id")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "phone_no")
	private String phoneNumber;

	@Column(name = "city")
	private String city;

	@Column(name = "additional_details")
	private String additionalDetails;

	@JsonManagedReference
	@OneToMany(mappedBy="guest", cascade=CascadeType.ALL)
	private List<Booking> bookings;


	public Guest() {

	}

	public Guest(String name , String phoneNumber, String city, String additionalDetails) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.city=city;
		this.additionalDetails=additionalDetails;
	}



}
