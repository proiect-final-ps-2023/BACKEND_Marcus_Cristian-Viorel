package com.hotel.hotelreservationsystem.service;

import com.hotel.hotelreservationsystem.model.Room;
import com.hotel.hotelreservationsystem.repository.RoomRepository;
import com.hotel.hotelreservationsystem.service.impl.RoomServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RoomServiceTest {

    private static final Long ID = 1L;
    private static final int NUMBER = 1;

    private static final Long ID_NOT = 2L;
    private static final int NUMBER_NOT = 2;

    private RoomServiceImpl roomServiceImpl;

    @Mock
    private RoomRepository roomRepository;

    private Room room;

    @BeforeEach
    void init() {
        initMocks(this);
        room = new Room();
        room.setId(ID);
        room.setNumber(NUMBER);

        roomServiceImpl = new RoomServiceImpl(roomRepository);

        // Create room
        when(roomRepository.save(any(Room.class))).thenReturn(room);

        // Find room by id
        when(roomRepository.findById(ID)).thenReturn(Optional.of(room));
        when(roomRepository.findById(ID_NOT)).thenReturn(Optional.empty());

        // Get all rooms
        when(roomRepository.findAll()).thenReturn(Collections.singletonList(room));

        // Delete room
        doNothing().when(roomRepository).deleteById(ID);
    }

    @Test
    void createRoom() {
        Room room = roomServiceImpl.createRoom(this.room);
        assertEquals(ID, room.getId());
        assertEquals(NUMBER, room.getNumber());
    }

    @Test
    void findRoomById() {
        Room room = roomServiceImpl.findRoomById(ID);
        assertEquals(ID, room.getId());
        assertEquals(NUMBER, room.getNumber());

        room = roomServiceImpl.findRoomById(ID_NOT);
        assertNull(room);
    }

    @Test
    void getAllRooms() {
        List<Room> rooms = roomServiceImpl.getAllRooms();
        assertEquals(1, rooms.size());
        assertEquals(ID, rooms.get(0).getId());
        assertEquals(NUMBER, rooms.get(0).getNumber());
    }

    @Test
    void deleteRoom() {
        roomServiceImpl.deleteRoom(this.room);
    }
}
