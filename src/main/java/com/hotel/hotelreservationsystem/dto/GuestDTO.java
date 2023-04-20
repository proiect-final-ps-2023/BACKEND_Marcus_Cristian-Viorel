package com.hotel.hotelreservationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuestDTO extends DTO {

    private Long id;
    private Long bookingId;
    private Long roomId;
    private Long guestDataId;

    @Override
    public String toString() {
        return "GuestDTO [id=" + id + ", bookingId=" + bookingId + ", roomId=" + roomId + ", guestDataId=" + guestDataId + "]";
    }
}
