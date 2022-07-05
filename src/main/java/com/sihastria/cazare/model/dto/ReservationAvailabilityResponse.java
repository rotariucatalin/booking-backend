package com.sihastria.cazare.model.dto;

import lombok.Data;

@Data
public class ReservationAvailabilityResponse {
    private boolean isAvailable;
    private int noRooms;
}
