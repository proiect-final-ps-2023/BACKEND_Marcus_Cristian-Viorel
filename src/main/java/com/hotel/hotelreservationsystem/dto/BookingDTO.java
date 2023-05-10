package com.hotel.hotelreservationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDTO extends DTO{

    private Long id;
    private Date checkInDate;
    private Date checkOutDate;

    private Double total;
    private Boolean isPaid;
    private Long userId;

    @Override
    public String toString() {
        return "BookingDTO [id=" + id + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", total=" + total + ", isPaid=" + isPaid + ", userId=" + userId + "]";
    }
}
