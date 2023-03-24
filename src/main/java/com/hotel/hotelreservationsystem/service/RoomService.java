package com.hotel.hotelreservationsystem.service;

import com.hotel.hotelreservationsystem.model.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoomService {

    // crud
    Room createRoom(Room room);

    Room findRoomById(Long id);

    List<Room> getAllRooms();

    Room updateRoom(Room room);

    Room deleteRoom(Room room);
}
