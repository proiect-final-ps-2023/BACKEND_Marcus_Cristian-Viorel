package com.hotel.hotelreservationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDTO extends DTO {

    private Long id;
    private int number;
    private Long roomTypeId;

    @Override
    public String toString() {
        return "RoomDTO [id=" + id + ", number=" + number + ", roomTypeId=" + roomTypeId + "]";
    }
}
