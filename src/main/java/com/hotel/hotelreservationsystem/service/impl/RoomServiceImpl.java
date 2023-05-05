package com.hotel.hotelreservationsystem.service.impl;

import com.hotel.hotelreservationsystem.model.Room;
import com.hotel.hotelreservationsystem.repository.RoomRepository;
import com.hotel.hotelreservationsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room findRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public Room findRoomByNumber(Integer roomNumber) {
        return roomRepository.findRoomByNumber(roomNumber);
    }

    @Override
    public List<Room> getAllRooms() {
        return (List<Room>) roomRepository.findAll();
    }

    @Override
    public List<Room> getAllRoomsWithRoomTypes() {
        return (List<Room>) roomRepository.getAllRoomsWithRoomTypes();
    }

    @Override
    public List<Room> getAllVacantRooms() {
        return (List<Room>) roomRepository.getAllVacantRooms();
    }

    @Override
    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room deleteRoom(Room room) {
        roomRepository.delete(room);
        return room;
    }
}
