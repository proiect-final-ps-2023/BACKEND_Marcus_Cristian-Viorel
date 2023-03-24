package com.hotel.hotelreservationsystem.service;

import com.hotel.hotelreservationsystem.model.RoomType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoomTypeService {

    // crud
    RoomType createRoomType(RoomType roomType);

    RoomType findRoomTypeById(Long id);

    List<RoomType> getAllRoomTypes();

    RoomType updateRoomType(RoomType roomType);

    RoomType deleteRoomType(RoomType roomType);
}
