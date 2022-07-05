package com.sihastria.cazare.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ReservationDto {

    private long roomId;
    private String name;
    private String phoneNumber;
    private String city;
    private String additionalDetails;
    private LocalDate startDate;
    private LocalDate endDate;
    private  int requiredBed;

}
