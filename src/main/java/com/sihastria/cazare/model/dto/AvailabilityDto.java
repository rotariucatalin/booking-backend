package com.sihastria.cazare.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class AvailabilityDto {

    private long bookingId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int requiredBed;
    private int totalBeds;


}
