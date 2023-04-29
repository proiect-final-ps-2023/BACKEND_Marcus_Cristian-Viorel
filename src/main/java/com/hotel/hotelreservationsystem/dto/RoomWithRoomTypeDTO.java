package com.hotel.hotelreservationsystem.dto;

import com.hotel.hotelreservationsystem.model.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomWithRoomTypeDTO extends DTO {

    // room specific fields
    private Long id;
    private int number;
    private Long roomTypeId;

    // roomtype specific fields
    private String name;
    private Double cost;
    private String description;

    @Override
    public String toString() {
        return "RoomWithRoomTypeDTO [id=" + id + ", number=" + number + ", roomTypeId=" + roomTypeId + ", name=" + name
                + ", price=" + cost + ", description=" + description + "]";
    }

    public static RoomWithRoomTypeDTO mapToDTO(Room room) {
        return new RoomWithRoomTypeDTO(room.getId(), room.getNumber(), room.getRoomType().getId(),
                room.getRoomType().getName(), room.getRoomType().getCost(), room.getRoomType().getDescription());
    }
}
