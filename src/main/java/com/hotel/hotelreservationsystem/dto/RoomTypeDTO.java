package com.hotel.hotelreservationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomTypeDTO extends DTO {

    private Long id;
    private String name;
    private Double price;
    private String description;

    @Override
    public String toString() {
        return "RoomTypeDTO [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + "]";
    }
}
