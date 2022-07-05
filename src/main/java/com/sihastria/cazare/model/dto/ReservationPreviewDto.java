package com.sihastria.cazare.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ReservationPreviewDto {

    private long id;
    private long room_id;
    private String name;
    private String additionalInformation;
    private int noBeds;
    private String phone;
    private String county;
    private LocalDate startDate;
    private LocalDate endDate;

}
