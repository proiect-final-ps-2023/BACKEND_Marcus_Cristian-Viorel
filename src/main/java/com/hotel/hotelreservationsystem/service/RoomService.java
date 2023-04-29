package com.hotel.hotelreservationsystem.service;

import com.hotel.hotelreservationsystem.model.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoomService {

    // crud
    Room createRoom(Room room);

    Room findRoomById(Long id);

    Room findRoomByNumber(Integer roomNumber);

    List<Room> getAllRooms();

    List<Room> getAllRoomsWithRoomTypes();

    Room updateRoom(Room room);

    Room deleteRoom(Room room);
}
